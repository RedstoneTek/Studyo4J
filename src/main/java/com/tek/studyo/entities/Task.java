package com.tek.studyo.entities;

import org.json.JSONObject;

public class Task {
	
	private String ownerId, configId, plannedDate, dueDate, assignmentDate, sectionId, type, icon, duePeriodTag, state, objectId, syncToken, contentObjectId, notes;
	private boolean important;
	
	public Task(JSONObject json) {
		this.ownerId = json.getString("ownerId");
		this.configId = json.getString("configId");
		this.plannedDate = json.getString("plannedDate");
		this.dueDate = json.getString("dueDate");
		this.assignmentDate = json.getString("assignmentDate");
		this.sectionId = JSONObject.NULL.equals(json.get("sectionId")) ? null : json.getString("sectionId");
		this.type = json.getString("type");
		this.icon = json.getString("icon");
		this.duePeriodTag = json.getString("duePeriodTag");
		this.state = json.getString("state");
		this.objectId = json.getString("objectId");
		this.syncToken = json.getString("syncToken");
		this.important = json.getBoolean("important");
		if(!json.has("masterContent")) return;
		JSONObject masterContent = json.getJSONObject("masterContent");
		this.contentObjectId = masterContent.getString("objectId");
		this.notes = JSONObject.NULL.equals(masterContent.get("notes")) ? null : masterContent.getString("notes");
	}

	public String getOwnerId() {
		return ownerId;
	}

	public String getConfigId() {
		return configId;
	}

	public String getPlannedDate() {
		return plannedDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getAssignmentDate() {
		return assignmentDate;
	}

	public String getSectionId() {
		return sectionId;
	}

	public String getType() {
		return type;
	}

	public String getIcon() {
		return icon;
	}

	public String getDuePeriodTag() {
		return duePeriodTag;
	}

	public String getState() {
		return state;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getSyncToken() {
		return syncToken;
	}

	public String getContentObjectId() {
		return contentObjectId;
	}

	public String getNotes() {
		return notes;
	}
	
	public boolean isImportant() {
		return important;
	}
	
}
