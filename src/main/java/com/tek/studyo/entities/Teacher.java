package com.tek.studyo.entities;

import org.json.JSONObject;

public class Teacher implements IUser, IHasAgenda {
	
	private String role, firstName, lastName, email, managedIdentifier, configId, objectId, syncToken;
	
	public Teacher(JSONObject json) {
		this.role = json.getString("role");
		this.firstName = json.getString("firstName");
		this.lastName = json.getString("lastName");
		this.email = json.getString("email");
		this.managedIdentifier = JSONObject.NULL.equals(json.get("managedIdentifier")) ? null : json.getString("managedIdentifier");
		this.configId = json.getString("configId");
		this.objectId = json.getString("objectId");
		this.syncToken = json.getString("syncToken");
	}

	@Override
	public boolean isValid() {
		return role != null && firstName != null && email != null;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	@Override
	public String getUserId() {
		return objectId;
	}
	
	@Override
	public String getRole() {
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

	@Override
	public String getConfigId() {
		return configId;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getSyncToken() {
		return syncToken;
	}
	
	@Override
	public String toString() {
		return getFullName();
	}
	
}
