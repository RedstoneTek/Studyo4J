package com.tek.studyo.entities;

import java.util.List;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class Student implements IUser{
	
	private String firstName, lastName, role, syncToken, configId, userId, email, objectId;
	private List<String> selectedSectionIds;
	
	public Student(JSONObject json) {
		this.firstName = json.getString("firstName");
		this.lastName = json.getString("lastName");
		this.role = json.getString("role");
		this.syncToken = json.getString("syncToken");
		this.configId = json.getString("configId");
		this.userId = json.getString("userId");
		this.email = json.getString("email");
		this.objectId = json.getString("objectId");
		this.selectedSectionIds = JSONUtil.parseArrayIntoString(json.getJSONObject("settings").getJSONArray("selectedSectionIds"));
	}
	
	public Student(JSONObject json, String listedStudent) {
		if(!json.has("firstName")) return;
		this.firstName = JSONObject.NULL.equals(json.get("firstName")) ? null : json.getString("firstName");
		this.lastName = JSONObject.NULL.equals(json.get("lastName")) ? null : json.getString("lastName");
		this.role = json.getString("role");
		this.syncToken = json.getString("syncToken");
		this.configId = json.getString("configId");
		this.email = JSONObject.NULL.equals(json.get("email")) ? null : json.getString("email");
		this.userId = json.getString("objectId");
		this.selectedSectionIds = JSONUtil.parseArrayIntoString(json.getJSONObject("settings").getJSONArray("selectedSectionIds"));
		if(!json.has("user")) return;
		JSONObject user = json.getJSONObject("user");
		this.objectId = user.getString("objectId");
	}
	
	@Override
	public boolean isValid() {
		return role != null;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String getRole() {
		return role;
	}

	public String getSyncToken() {
		return syncToken;
	}

	@Override
	public String getConfigId() {
		return configId;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getObjectId() {
		return objectId;
	}

	public List<String> getSelectedSectionIds() {
		return selectedSectionIds;
	}
	
}
