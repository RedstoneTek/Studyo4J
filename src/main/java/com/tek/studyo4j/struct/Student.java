package com.tek.studyo4j.struct;

import java.util.List;

public class Student implements IUser {
	
	private Role role;
	private String configId;
	private String firstName;
	private String lastName;
	private String email;
	private String managedIdentifier;
	private Settings settings;
	private List<String> childrenAccountIds;
	private String objectId;
	private String syncToken;
	
	public Role getRole() {
		return role;
	}
	
	public String getConfigId() {
		return configId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getManagedIdentifier() {
		return managedIdentifier;
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public List<String> getChildrenAccountIds() {
		return childrenAccountIds;
	}
	
	public String getObjectId() {
		return objectId;
	}
	
	public String getSyncToken() {
		return syncToken;
	}
	
	public boolean isValid() {
		return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty() && email != null && !email.isEmpty();
	}
	
	public StudentSchedule getStudentSchedule(Configuration configuration) {
		return new StudentSchedule(configuration, this);
	}
	
}
