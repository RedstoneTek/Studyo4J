package com.tek.studyo.entities;

public class AuthenticatedUser {
	
	private String objectId;
	private String username;
	private String email;
	private String _email_verify_token;
	private String _email_verify_token_at;
	private String createdAt;
	private String updatedAt;
	private String sessionToken;
	private boolean emailVerified;

	public String getObjectId() {
		return objectId;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getEmailVerifyToken() {
		return _email_verify_token;
	}

	public String getEmailVerifyTokenAt() {
		return _email_verify_token_at;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}
	
}
