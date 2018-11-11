package com.tek.studyo.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class Period {
	
	private String id, scheduleTag, startTime, endTime, tag, kind;
	private boolean isCustomSchedule;
	private List<Course> courseOccurrences;
	
	public Period(JSONObject json) {
		this.id = json.getString("id");
		this.scheduleTag = json.getString("scheduleTag");
		this.startTime = json.getString("startTime");
		this.endTime = json.getString("endTime");
		this.tag = json.getString("tag");
		this.kind = json.getString("kind");
		this.isCustomSchedule = json.getBoolean("isCustomSchedule");
		this.courseOccurrences = JSONUtil.parseArrayIntoJSON(json.getJSONArray("courseOccurrences")).stream().map(jsonobj -> new Course(jsonobj)).collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public String getScheduleTag() {
		return scheduleTag;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getTag() {
		return tag;
	}

	public String getKind() {
		return kind;
	}

	public boolean isCustomSchedule() {
		return isCustomSchedule;
	}

	public List<Course> getCourseOccurrences() {
		return courseOccurrences;
	}
	
}
