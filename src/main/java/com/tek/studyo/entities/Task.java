package com.tek.studyo.entities;

import org.json.JSONObject;

public class Task {
	
	private String title, notes, icon, assignmentDate, plannedDate, dueDate, duePeriodTag, configId, ownerId, sectionId, objectId, syncToken;
	private boolean important;
	
	public Task(JSONObject json) {
		if(json.has("masterContent")) {
			JSONObject master = json.getJSONObject("masterContent");
			this.title = JSONObject.NULL.equals(json.get("title")) ? null : json.getString("title");
			this.notes = JSONObject.NULL.equals(master.get("notes")) ? null : master.getString("notes");
			this.icon = json.getString("icon");
			this.assignmentDate = json.getString("assignmentDate");
			this.plannedDate = json.getString("plannedDate");
			this.dueDate = json.getString("dueDate");
			this.duePeriodTag = JSONObject.NULL.equals(json.get("duePeriodTag")) ? null : json.getString("duePeriodTag");
			this.configId = json.getString("configId");
			this.ownerId = json.getString("ownerId");
			this.sectionId = json.getString("sectionId");
			this.objectId = json.getString("objectId");
			this.syncToken = json.getString("syncToken");
			this.important = json.getBoolean("important");
		}else {
			this.title = JSONObject.NULL.equals(json.get("title")) ? null : json.getString("title");
			this.notes = JSONObject.NULL.equals(json.get("notes")) ? null : json.getString("notes");
			this.icon = json.getString("icon");
			this.assignmentDate = json.getString("assignmentDate");
			this.plannedDate = json.getString("plannedDate");
			this.dueDate = json.getString("dueDate");
			this.duePeriodTag = JSONObject.NULL.equals(json.get("duePeriodTag")) ? null : json.getString("duePeriodTag");
			this.configId = json.getString("configId");
			this.ownerId = json.getString("ownerId");
			this.sectionId = JSONObject.NULL.equals(json.get("sectionId")) ? null : json.getString("sectionId");
			this.objectId = json.getString("objectId");
			this.syncToken = json.getString("syncToken");
			this.important = json.getBoolean("important");
		}
	}

	public String getTitle() {
		return title;
	}

	public String getNotes() {
		return notes;
	}

	public String getIcon() {
		return icon;
	}

	public String getAssignmentDate() {
		return assignmentDate;
	}

	public String getPlannedDate() {
		return plannedDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getDuePeriodTag() {
		return duePeriodTag;
	}

	public String getConfigId() {
		return configId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getSyncToken() {
		return syncToken;
	}

	public boolean isImportant() {
		return important;
	}
	
}
