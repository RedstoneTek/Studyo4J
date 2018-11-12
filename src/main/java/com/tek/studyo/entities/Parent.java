package com.tek.studyo.entities;

import java.util.List;

import org.json.JSONObject;

public class Parent implements IUser{
	
	private String configId, email, role, userId, objectId, syncToken, username;
	private List<String> childrenAccountIds;
	
	public Parent(JSONObject json) {
		this.configId = json.getString("configId");
		this.email = json.getString("email");
		this.role = json.getString("role");
		this.userId = json.getString("objectId");
		this.syncToken = json.getString("syncToken");
		if(!json.has("user")) return;
		JSONObject user = json.getJSONObject("user");
		this.objectId = user.getString("objectId");
		this.username = user.getString("username");
	}
	
	@Override
	public boolean isValid() {
		return role != null;
	}

	@Override
	public String getConfigId() {
		return configId;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getRole() {
		return role;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getSyncToken() {
		return syncToken;
	}

	public String getUsername() {
		return username;
	}

	public List<String> getChildrenAccountIds() {
		return childrenAccountIds;
	}
	
}
