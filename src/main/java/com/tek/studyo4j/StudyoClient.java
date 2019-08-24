package com.tek.studyo4j;

public class StudyoClient {
	
	private static final String STUDYO_API_HOST = "https://api.studyo.app/";
	
	private String authorization;
	
	public StudyoClient(String authorization) {
		this.authorization = authorization;
	}
	
}
