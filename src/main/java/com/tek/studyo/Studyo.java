package com.tek.studyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.tek.studyo.entities.AuthenticatedUser;
import com.tek.studyo.entities.UserCredentials;
import com.tek.studyo.exceptions.StudyoAuthenticationException;

public class Studyo {
	
	private static final String ENDPOINT_LOGIN    = "https://parse-server.k8s-prod.infra.studyo.co/parse/login";
	private static final String CONTENT_TYPE      = "application/json; charset=utf-8";
	private static final short  POOL_THREAD_COUNT = 4;
	
	private static Gson gson;
	private static ExecutorService threadPool;

	public static void initialize() {
		gson = new Gson();
		threadPool = Executors.newFixedThreadPool(POOL_THREAD_COUNT);
	}
	
	public static CompletableFuture<AuthenticatedUser> authenticate(UserCredentials credentials) {
		CompletableFuture<AuthenticatedUser> future = new CompletableFuture<AuthenticatedUser>();
		
		threadPool.execute(() -> {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(ENDPOINT_LOGIN);
			String requestPayload = gson.toJson(credentials);
			httpPost.setEntity(new StringEntity(requestPayload, Consts.UTF_8));
			httpPost.setHeader("Content-Type", CONTENT_TYPE);
			
			try {
				CloseableHttpResponse response = httpClient.execute(httpPost);
				HttpEntity responseEntity = response.getEntity();
				
				BufferedReader responseReader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
				StringBuffer content = new StringBuffer();
				String line;
				while((line = responseReader.readLine()) != null) {
					content.append(line);
				}
				
				JSONObject jsonResponse = new JSONObject(content.toString());
				
				if(jsonResponse.has("error")) {
					future.completeExceptionally(new StudyoAuthenticationException());
				} else {
					AuthenticatedUser user = gson.fromJson(jsonResponse.toString(), AuthenticatedUser.class);
					
					future.complete(user);
				}
				
				EntityUtils.consume(responseEntity);
				response.close();
			} catch (IOException e) {
				future.completeExceptionally(e);
			}
		});
		
		return future;
	}
	
	public static void shutdown() {
		threadPool.shutdown();
	}
	
}
