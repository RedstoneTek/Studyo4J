package com.tek.studyo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.tek.studyo.entities.Calendar;
import com.tek.studyo.entities.IUser;
import com.tek.studyo.entities.Parent;
import com.tek.studyo.entities.School;
import com.tek.studyo.entities.Section;
import com.tek.studyo.entities.Student;
import com.tek.studyo.entities.Task;
import com.tek.studyo.entities.Teacher;
import com.tek.studyo.exceptions.StudyoAuthenticationException;
import com.tek.studyo.exceptions.StudyoQueryException;
import com.tek.studyo.util.JSONUtil;

/**
 * Studyo.class
 * 
 * This class is the way to go to interface with the Studyo API
 * 
 * @author RedstoneTek
 */
public class Studyo {
	
	//Constant values
	private static final String ENDPOINT_LOGIN = "https://parse-server.k8s-prod.infra.studyo.co/parse/login";
	private static final String ENDPOINT_USERID = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/users/";
	private static final String ENDPOINT_CONFIG = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/configs/";
	private static final String ENDPOINT_USER = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/accounts/";
	private static final String ENDPOINT_TASKS = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/contents?accountId=%s&configId=%s";
	private static final String ENDPOINT_CALENDAR = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/configs/%s/generated_calendar?accountId=%s";
	private static final String ENDPOINT_USERS = "https://rest-api.k8s-prod.infra.studyo.co/api/v2/accounts?configId=%s";
	private static final String METHOD_LOGIN = "GET";
	private static final String PARAM_SESSION_TOKEN = "x-session-token";
	private static final String APPLICATION_ID = "tSkkxuzQnCzanHYzcQwqhlTHY3ROmtvKYQmYW0J8";
	
	//Dynamic values
	private static String OBJECT_ID, CONFIG_ID, USER_ID, SESSION_TOKEN;
	
	/**
	 * This method authenticates the Studyo4J library as the specified user
	 * It is used to obtain an object id, a config id, a user id aswell as a session token
	 * 
	 * @param username This is the username or email of the user to authenticate as
	 * @param password This is the password of the user to authenticate as
	 * @throws StudyoAuthenticationException This can occur if the request fails to send/receive or if the credentials are incorrect
	 */
	public static void authenticate(String username, String password) throws StudyoAuthenticationException {
		try {
			JSONObject request = new JSONObject();
			request.put("username", username);
			request.put("password", password);
			request.put("_method", METHOD_LOGIN);
			request.put("_ApplicationId", APPLICATION_ID);
			
			HttpResponse<JsonNode> response = 
				Unirest.post(ENDPOINT_LOGIN)
				.body(request)
				.asJson();
			
			if(response.getStatus() == 200) {
				JSONObject jsonResponse = response.getBody().getObject();
				SESSION_TOKEN = jsonResponse.getString("sessionToken");
				OBJECT_ID = jsonResponse.getString("objectId");
				
				HttpResponse<JsonNode> response2 = 
					Unirest.get(ENDPOINT_USERID + OBJECT_ID)
					.header(PARAM_SESSION_TOKEN, SESSION_TOKEN)
					.asJson();
				
				if(response2.getStatus() == 200) {
					JSONObject jsonResponse2 = response2.getBody().getObject();
					String email = jsonResponse2.getString("email");
					JSONArray accounts = jsonResponse2.getJSONArray("accounts");
					Iterator<Object> accountIterator = accounts.iterator();
					while(accountIterator.hasNext()) {
						Object accountObject = accountIterator.next();
						JSONObject account = (JSONObject) accountObject;
						if(!JSONObject.NULL.equals(account.get("email")) && account.getString("email").equals(email)) {
							CONFIG_ID = account.getString("configId");
							USER_ID = account.getString("objectId");
						}
					}
				}else {
					throw new StudyoAuthenticationException();
				}
			}else {
				throw new StudyoAuthenticationException();
			}
		}catch(Exception e) {
			throw new StudyoAuthenticationException();
		}
	}
	
	/**
	 * This method fetches the School object of the current authenticated user
	 * The school object contains the name, year, schedules and classes (sections)
	 * 
	 * @return This returns the School object which the user is a part of
	 * @throws StudyoQueryException It can throw this error if the request fails to send/receive or if the session token is invalid
	 */
	public static School getSchoolConfiguration() throws StudyoQueryException{
		try {
			HttpResponse<JsonNode> response = 
				Unirest.get(ENDPOINT_CONFIG + CONFIG_ID)
				.header(PARAM_SESSION_TOKEN, SESSION_TOKEN)
				.asJson();
			
			if(response.getStatus() == 200) {
				JSONObject jsonResponse = response.getBody().getObject();
				return new School(jsonResponse);
			}else {
				throw new StudyoQueryException();
			}
		}catch(Exception e) {
			throw new StudyoQueryException();
		}
	}
	
	/**
	 * This method fetches the currently authenticated Student object
	 * The Student object contains first name, last name, email, role, syncToken
	 * 
	 * @return This returns the currently authenticated Student object
	 * @throws StudyoQueryException It can throw this error if the request fails to send/receive or if the session token is invalid
	 */
	public static Student getUserConfiguration() throws StudyoQueryException {
		try {
			HttpResponse<JsonNode> response = 
				Unirest.get(ENDPOINT_USER + USER_ID)
				.header(PARAM_SESSION_TOKEN, SESSION_TOKEN)
				.asJson();
			
			if(response.getStatus() == 200) {
				JSONObject jsonResponse = response.getBody().getObject();
				return new Student(jsonResponse);
			}else {
				throw new StudyoQueryException();
			}
		}catch(Exception e) {
			throw new StudyoQueryException();
		}
	}
	
	/**
	 * This method returns the user tasks under the currently authenticated user
	 * The user tasks contain exams, homework, notes and anything written down in the schedule
	 * 
	 * @return This returns the list of Tasks under the currently authenticated user
	 * @throws StudyoQueryException It can throw this error if the request fails to send/receive or if the session token is invalid
	 */
	public static List<Task> getUserTasks() throws StudyoQueryException{
		try {
			HttpResponse<JsonNode> response = 
				Unirest.get(String.format(ENDPOINT_TASKS, USER_ID, CONFIG_ID))
				.header(PARAM_SESSION_TOKEN, SESSION_TOKEN)
				.asJson();
			
			if(response.getStatus() == 200) {
				ArrayList<Task> userTasks = new ArrayList<Task>();
				JSONArray jsonResponse = response.getBody().getArray();
				JSONUtil.parseArrayIntoJSON(jsonResponse).forEach(jsonObject -> {
					userTasks.add(new Task(jsonObject));
				});
				return userTasks;
			}else {
				throw new StudyoQueryException();
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new StudyoQueryException();
		}
	}
	
	/**
	 * This method returns the Calendar object associated with the currently authenticated user
	 * The Calendar object contains the times, courses, timezone, cycleDay, etc of every day of the year
	 * 
	 * @return Returns the calendar object associated with the currently authenticated user
	 * @throws StudyoQueryException It can throw this error if the request fails to send/receive or if the session token is invalid
	 */
	public static Calendar getCalendar() throws StudyoQueryException{
		try {
			HttpResponse<JsonNode> response = 
				Unirest.get(String.format(ENDPOINT_CALENDAR, CONFIG_ID, USER_ID))
				.header(PARAM_SESSION_TOKEN, SESSION_TOKEN)
				.asJson();
			
			if(response.getStatus() == 200) {
				JSONObject jsonResponse = response.getBody().getObject();
				return new Calendar(jsonResponse);
			}else {
				throw new StudyoQueryException();
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new StudyoQueryException();
		}
	}
	
	/**
	 * This method fetches a list of all users under the current school, this includes students, parents and teachers
	 * The IUser object gives access to the object id, user id, role and the underlying class
	 * 
	 * @return This returns a list of all users under the current school
	 * @throws StudyoQueryException
	 */
	public static List<IUser> getUsers() throws StudyoQueryException{
		try {
			HttpResponse<JsonNode> response = 
				Unirest.get(String.format(ENDPOINT_USERS, CONFIG_ID))
				.header(PARAM_SESSION_TOKEN, SESSION_TOKEN)
				.asJson();
			
			if(response.getStatus() == 200) {
				ArrayList<IUser> users = new ArrayList<IUser>();
				JSONArray jsonResponse = response.getBody().getArray();
				JSONUtil.parseArrayIntoJSON(jsonResponse).forEach(jsonObject -> {
					switch(jsonObject.getString("role")) {
					case "student":
						users.add(new Student(jsonObject, null));
						break;
					case "parent":
						users.add(new Parent(jsonObject));
						break;
					case "teacher":
						users.add(new Teacher(jsonObject));
						break;
					default:
						break;
					}
				});
				return users.stream().filter(IUser::isValid).collect(Collectors.toList());
			}else {
				throw new StudyoQueryException();
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new StudyoQueryException();
		}
	}
	
	public static com.tek.studyo.entities.simplified.Calendar getSimplifiedCalendar() throws StudyoQueryException {
		return new com.tek.studyo.entities.simplified.Calendar(getSchoolConfiguration(), getCalendar(), getUsers(), getUserTasks());
	}
	
	public static List<Student> getStudents() throws StudyoQueryException {
		return getUsers().stream()
				.filter(user -> user.getRole().equals("student"))
				.map(user -> (Student)user)
				.collect(Collectors.toList());
	}
	
	public static List<Parent> getParents() throws StudyoQueryException {
		return getUsers().stream()
				.filter(user -> user.getRole().equals("parent"))
				.map(user -> (Parent)user)
				.collect(Collectors.toList());
	}
	
	public static List<Teacher> getTeachers() throws StudyoQueryException {
		return getUsers().stream()
				.filter(user -> user.getRole().equals("teacher"))
				.map(user -> (Teacher)user)
				.collect(Collectors.toList());
	}
	
	public static List<Student> getStudents(List<IUser> users) {
		return users.stream()
				.filter(user -> user.getRole().equals("student"))
				.map(user -> (Student)user)
				.collect(Collectors.toList());
	}
	
	public static List<Parent> getParents(List<IUser> users) {
		return users.stream()
				.filter(user -> user.getRole().equals("parent"))
				.map(user -> (Parent)user)
				.collect(Collectors.toList());
	}
	
	public static List<Teacher> getTeachers(List<IUser> users) {
		return users.stream()
				.filter(user -> user.getRole().equals("teacher"))
				.map(user -> (Teacher)user)
				.collect(Collectors.toList());
	}
	
	public static Optional<IUser> getUserById(String id) throws StudyoQueryException {
		return getUsers().stream()
				.filter(user -> user.getUserId().equals(id))
				.findFirst();
	}
	
	public static Optional<IUser> getUserById(List<IUser> users, String id) {
		return users.stream()
				.filter(user -> user.getUserId().equals(id))
				.findFirst();
	}
	
	public static Optional<Student> getStudentById(String id) throws StudyoQueryException {
		return getUsers().stream()
				.filter(user -> user.getRole().equals("student"))
				.filter(user -> user.getUserId().equals(id))
				.map(user -> (Student)user)
				.findFirst();
	}
	
	public static Optional<Student> getStudentById(List<IUser> users, String id) {
		return users.stream()
				.filter(user -> user.getRole().equals("student"))
				.filter(user -> user.getUserId().equals(id))
				.map(user -> (Student)user)
				.findFirst();
	}
	
	public static Optional<Parent> getParentById(String id) throws StudyoQueryException {
		return getUsers().stream()
				.filter(user -> user.getRole().equals("parent"))
				.filter(user -> user.getUserId().equals(id))
				.map(user -> (Parent)user)
				.findFirst();
	}
	
	public static Optional<Parent> getParentById(List<IUser> users, String id) {
		return users.stream()
				.filter(user -> user.getRole().equals("parent"))
				.filter(user -> user.getUserId().equals(id))
				.map(user -> (Parent)user)
				.findFirst();
	}
	
	public static Optional<Teacher> getTeacherById(String id) throws StudyoQueryException {
		return getUsers().stream()
				.filter(user -> user.getRole().equals("teacher"))
				.filter(user -> user.getUserId().equals(id))
				.map(user -> (Teacher)user)
				.findFirst();
	}
	
	public static Optional<Teacher> getTeacherById(List<IUser> users, String id) {
		return users.stream()
				.filter(user -> user.getRole().equals("teacher"))
				.filter(user -> user.getUserId().equals(id))
				.map(user -> (Teacher)user)
				.findFirst();
	}
	
	public static Optional<Section> getSectionByTeacherId(String teacherId) throws StudyoQueryException{
		return getSchoolConfiguration().getSections().stream()
				.filter(section -> section.getDefaultTeacherId().equals(teacherId))
				.findFirst();
	}
	
	public static Optional<Section> getSectionByTeacherId(School school, String teacherId){
		return school.getSections().stream()
				.filter(section -> section.getDefaultTeacherId().equals(teacherId))
				.findFirst();
	}
	
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String getDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(date);
	}
	
	public static String getObjectId() {
		return OBJECT_ID;
	}
	
	public static String getConfigId() {
		return CONFIG_ID;
	}
	
	public static String getUserId() {
		return USER_ID;
	}
	
	public static String getSessionToken() {
		return SESSION_TOKEN;
	}
}
