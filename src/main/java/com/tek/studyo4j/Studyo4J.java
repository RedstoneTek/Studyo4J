package com.tek.studyo4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tek.studyo4j.struct.Configuration;
import com.tek.studyo4j.struct.IUser;
import com.tek.studyo4j.struct.Parent;
import com.tek.studyo4j.struct.Role;
import com.tek.studyo4j.struct.Role.RoleTypeAdapter;
import com.tek.studyo4j.struct.Student;
import com.tek.studyo4j.struct.Teacher;

@SuppressWarnings("deprecation")
public class Studyo4J {
	
	private static final HttpClient CLIENT = new DefaultHttpClient();
	private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Role.class, new RoleTypeAdapter()).create();
	private static final String APPLICATION_ID = "tSkkxuzQnCzanHYzcQwqhlTHY3ROmtvKYQmYW0J8";
	
	private static final String ENDPOINT_LOGIN = "https://parse-server.k8s-prod.infra.studyo.co/parse/login";
	private static final String ENDPOINT_USERS = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/users/%s";
	private static final String ENDPOINT_USER = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/accounts/%s";
	private static final String ENDPOINT_CONFIGURATION_USERS = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/accounts?configId=%s";
	private static final String ENDPOINT_CONFIGURATION = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/configs/%s";
	
	private String sessionToken;
	private String userId;
	
	public Studyo4J(String sessionToken, String userId) throws IOException {
		this.sessionToken = sessionToken;
		this.userId = userId;
	}
	
	public IUser getSelf() throws IOException {
		List<IUser> users = getUsers(userId);
		return users.get(users.size() - 1);
	}
	
	public List<IUser> getUsers(String userId) throws IOException {
		JSONObject response = new JSONObject(httpGet(String.format(ENDPOINT_USERS, userId), headers()));
		JSONArray userArray = response.getJSONArray("accounts");
		
		List<IUser> users = new ArrayList<IUser>();
		Iterator<Object> arrayIterator = userArray.iterator();
		while(arrayIterator.hasNext()) {
			JSONObject jsonUser = (JSONObject) arrayIterator.next();
			Optional<Role> roleOpt = Role.getRole(jsonUser.getString("role"));
			if(roleOpt.isPresent()) {
				if(roleOpt.get().equals(Role.STUDENT)) users.add(GSON.fromJson(jsonUser.toString(), Student.class));
				if(roleOpt.get().equals(Role.TEACHER)) users.add(GSON.fromJson(jsonUser.toString(), Teacher.class));
				if(roleOpt.get().equals(Role.PARENT)) users.add(GSON.fromJson(jsonUser.toString(), Parent.class));
			}
		}
		
		return users.stream().filter(IUser::isValid).collect(Collectors.toList());
	}
	
	public IUser getUser(String objectId) throws IOException {
		JSONObject response = new JSONObject(httpGet(String.format(ENDPOINT_USER, objectId), headers()));
		
		Optional<Role> roleOpt = Role.getRole(response.getString("role"));
		if(roleOpt.isPresent()) {
			if(roleOpt.get().equals(Role.STUDENT)) return GSON.fromJson(response.toString(), Student.class);
			if(roleOpt.get().equals(Role.TEACHER)) return GSON.fromJson(response.toString(), Teacher.class);
			if(roleOpt.get().equals(Role.PARENT)) return GSON.fromJson(response.toString(), Parent.class);
		}
		
		return null;
	}
	
	public List<IUser> getConfigurationUsers(String configurationId) throws IOException {
		JSONArray response = new JSONArray(httpGet(String.format(ENDPOINT_CONFIGURATION_USERS, configurationId), headers()));
		
		List<IUser> users = new ArrayList<IUser>();
		Iterator<Object> arrayIterator = response.iterator();
		while(arrayIterator.hasNext()) {
			JSONObject jsonUser = (JSONObject) arrayIterator.next();
			Optional<Role> roleOpt = Role.getRole(jsonUser.getString("role"));
			if(roleOpt.isPresent()) {
				if(roleOpt.get().equals(Role.STUDENT)) users.add(GSON.fromJson(jsonUser.toString(), Student.class));
				if(roleOpt.get().equals(Role.TEACHER)) users.add(GSON.fromJson(jsonUser.toString(), Teacher.class));
				if(roleOpt.get().equals(Role.PARENT)) users.add(GSON.fromJson(jsonUser.toString(), Parent.class));
			}
		}
		
		return users.stream().filter(IUser::isValid).collect(Collectors.toList());
	}
	
	public Configuration getConfiguration(String configurationId) throws IOException {
		JSONObject response = new JSONObject(httpGet(String.format(ENDPOINT_CONFIGURATION, configurationId), headers()));
		Configuration configuration = GSON.fromJson(response.toString(), Configuration.class);
		return configuration;
	}
	
	private Map<String, String> headers() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Session-Token", sessionToken);
		return headers;
	}
	
	public String getSessionToken() {
		return sessionToken;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public static Studyo4J login(String username, String password) throws IOException, StudyoAuthenticationException {
		JSONObject payload = new JSONObject();
		payload.put("username", username);
		payload.put("password", password);
		payload.put("_method", "GET");
		payload.put("_ApplicationId", APPLICATION_ID);
		
		JSONObject response = new JSONObject(httpPost(ENDPOINT_LOGIN, json(payload)));
		if(response.has("error")) throw new StudyoAuthenticationException(response.getString("error"));
		return new Studyo4J(response.getString("sessionToken"), response.getString("objectId"));
	}
	
	private static String httpGet(String url, Map<String, String> headers) throws IOException {
		HttpGet request = new HttpGet(url);
		headers.keySet().forEach(key -> request.addHeader(key, headers.get(key)));
		HttpResponse response = CLIENT.execute(request);
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder responseBuilder = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
			responseBuilder.append(line + "\n");
		}
		if(responseBuilder.length() > 0) responseBuilder.setLength(responseBuilder.length() - 1);
		
		return responseBuilder.toString();
	}
	
	private static String httpPost(String url, HttpEntity requestEntity) throws IOException {
		return httpPost(url, requestEntity, new HashMap<String, String>());
	}
	
	private static String httpPost(String url, HttpEntity requestEntity, Map<String, String> headers) throws IOException {
		HttpPost request = new HttpPost(url);
		request.setEntity(requestEntity);
		headers.keySet().forEach(key -> request.addHeader(key, headers.get(key)));
		HttpResponse response = CLIENT.execute(request);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder responseBuilder = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
			responseBuilder.append(line + "\n");
		}
		if(responseBuilder.length() > 0) responseBuilder.setLength(responseBuilder.length() - 1);
		
		return responseBuilder.toString();
	}
	
	private static HttpEntity json(JSONObject json) {
		return new StringEntity(json.toString(), Charset.defaultCharset());
	}
	
}
