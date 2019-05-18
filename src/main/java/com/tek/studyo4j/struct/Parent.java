package com.tek.studyo4j.struct;

import java.util.List;

public class Parent implements IUser {
	
	private Role role;
	private String configId;
	private String email;
	private List<String> childrenAccountPendingVerificationIds;
	private List<String> childrenAccountIds;
	private Settings settings;
	private String managedIdentifier;
	private String firstName;
	private String lastName;
	private String objectId;
	private String syncToken;
	
	public Role getRole() {
		return role;
	}
	
	public String getConfigId() {
		return configId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public List<String> getChildrenAccountPendingVerificationIds() {
		return childrenAccountPendingVerificationIds;
	}
	
	public List<String> getChildrenAccountIds() {
		return childrenAccountIds;
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public String getManagedIdentifier() {
		return managedIdentifier;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getObjectId() {
		return objectId;
	}
	
	public String getSyncToken() {
		return syncToken;
	}

	public boolean isValid() {
		return email != null && !email.isEmpty();
	}
	
}
