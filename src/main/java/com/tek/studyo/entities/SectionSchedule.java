package com.tek.studyo.entities;

import java.util.List;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class SectionSchedule {
	
	private int cycleDay;
	private String roomName, id, periodTag;
	private List<String> teacherIds;
	
	public SectionSchedule(JSONObject json) {
		this.cycleDay = JSONObject.NULL.equals(json.get("cycleDay")) ? 0 : json.getInt("cycleDay");
		this.roomName = JSONObject.NULL.equals(json.get("roomName")) ? null : json.getString("roomName");
		this.id = JSONObject.NULL.equals(json.get("id")) ? null : json.getString("id");
		this.periodTag = JSONObject.NULL.equals(json.get("periodTag")) ? null : json.getString("periodTag");
		this.teacherIds = JSONUtil.parseArrayIntoString(json.getJSONArray("teacherIds"));
	}

	public int getCycleDay() {
		return cycleDay;
	}

	public String getRoomName() {
		return roomName;
	}

	public String getId() {
		return id;
	}

	public String getPeriodTag() {
		return periodTag;
	}

	public List<String> getTeacherIds() {
		return teacherIds;
	}
	
}
