package com.tek.studyo4j.struct;

import java.util.List;
import java.util.stream.Collectors;

public class Teacher implements IUser {
	
	private Role role;
	private String firstName;
	private String lastName;
	private String email;
	private String managedIdentifier;
	private String configId;
	private Settings settings;
	private boolean isAutomatchPendingEmailVerification;
	private String language;
	private boolean isDeleted;
	private String identityUserId;
	private List<String> childrenAccountIds;
	private List<String> childrenAccountPendingVerificationIds;
	private List<String> manualChanges;
	private String objectId;
	private String syncToken;
	
	public Role getRole() {
		return role;
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
	
	public String getConfigId() {
		return configId;
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public boolean isAutomatchPendingEmailVerification() {
		return isAutomatchPendingEmailVerification;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	public String getIdentityUserId() {
		return identityUserId;
	}
	
	public List<String> getChildrenAccountIds() {
		return childrenAccountIds;
	}
	
	public List<String> getChildrenAccountPendingVerificationIds() {
		return childrenAccountPendingVerificationIds;
	}
	
	public List<String> getManualChanges() {
		return manualChanges;
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
	
	public List<Section> getTaughtSections(Configuration configuration) {
		return configuration.getSections().stream()
				.filter(section -> section.getDefaultTeacherId().equals(objectId))
				.collect(Collectors.toList());
	}
		
}