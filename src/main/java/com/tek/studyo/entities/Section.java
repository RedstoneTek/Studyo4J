package com.tek.studyo.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class Section {
	
	private boolean hidden, locked;
	private String sectionNumber, defaultRoomName, title, managedIdentifier, gradeLevel, objectId, defaultTeacherId;
	private List<SectionSchedule> schedules;
	
	public Section(JSONObject json) {
		this.hidden = json.getBoolean("hidden");
		this.locked = json.getBoolean("locked");
		this.sectionNumber = JSONObject.NULL.equals(json.get("sectionNumber")) ? null : json.getString("sectionNumber");
		this.defaultRoomName = JSONObject.NULL.equals(json.get("defaultRoomName")) ? null : json.getString("defaultRoomName");
		this.title = json.getString("title");
		this.managedIdentifier = json.getString("managedIdentifier");
		this.gradeLevel = JSONObject.NULL.equals(json.get("gradeLevel")) ? null : json.getString("gradeLevel");
		this.objectId = json.getString("objectId");
		this.defaultTeacherId = json.get("defaultTeacherId").equals(JSONObject.NULL) ? null : json.getString("defaultTeacherId");
		this.schedules = JSONUtil.parseArrayIntoJSON(json.getJSONArray("schedules")).stream().map(jsonobj -> new SectionSchedule(jsonobj)).collect(Collectors.toList());
	}

	public boolean isHidden() {
		return hidden;
	}

	public boolean isLocked() {
		return locked;
	}

	public String getSectionNumber() {
		return sectionNumber;
	}

	public String getDefaultRoomName() {
		return defaultRoomName;
	}

	public String getTitle() {
		return title;
	}

	public String getManagedIdentifier() {
		return managedIdentifier;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getDefaultTeacherId() {
		return defaultTeacherId;
	}

	public List<SectionSchedule> getSchedules() {
		return schedules;
	}
	
}
