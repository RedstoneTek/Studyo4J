package com.tek.studyo.entities;

public class UserCredentials {
	
	private final static String APPLICATION_ID = "tSkkxuzQnCzanHYzcQwqhlTHY3ROmtvKYQmYW0J8";
	
	private String username;
	private String password;
	private String _method = "GET";
	private String _ApplicationId = APPLICATION_ID;
	
	public UserCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getMethod() {
		return _method;
	}
	
	public String getApplicationId() {
		return _ApplicationId;
	}
	
}
