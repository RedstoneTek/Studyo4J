package com.tek.studyo.entities;

import java.util.List;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class Course {
	
	private String sectionId, roomName, customTitle, customRoomName;
	private int ordinal, normalizedOrdinal, termOrdinal, yearRemaining, termRemaining;
	private boolean skipped;
	private List<String> teacherIds;
	
	public Course(JSONObject json) {
		this.sectionId = json.getString("sectionId");
		this.roomName = json.getString("roomName");
		this.customTitle = json.getString("customTitle");
		this.customRoomName = json.getString("customRoomName");
		this.ordinal = json.getInt("ordinal");
		this.normalizedOrdinal = json.getInt("normalizedOrdinal");
		this.termOrdinal = json.getInt("termOrdinal");
		this.yearRemaining = json.getInt("yearRemaining");
		this.termRemaining = json.getInt("termRemaining");
		this.skipped = json.getBoolean("skipped");
		this.teacherIds = JSONUtil.parseArrayIntoString(json.getJSONArray("teacherIds"));
	}

	public String getSectionId() {
		return sectionId;
	}

	public String getRoomName() {
		return roomName;
	}

	public String getCustomTitle() {
		return customTitle;
	}

	public String getCustomRoomName() {
		return customRoomName;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public int getNormalizedOrdinal() {
		return normalizedOrdinal;
	}

	public int getTermOrdinal() {
		return termOrdinal;
	}

	public int getYearRemaining() {
		return yearRemaining;
	}

	public int getTermRemaining() {
		return termRemaining;
	}

	public boolean isSkipped() {
		return skipped;
	}

	public List<String> getTeacherIds() {
		return teacherIds;
	}
	
}
