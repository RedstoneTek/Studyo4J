package com.tek.studyo.entities;

import org.json.JSONObject;

public class DayConfiguration {
	
	private String date, id, scheduleId;
	
	public DayConfiguration(JSONObject json) {
		this.date = JSONObject.NULL.equals(json.get("date")) ? null : json.getString("date");
		this.id = json.getString("id");
		this.scheduleId = JSONObject.NULL.equals(json.get("scheduleId")) ? null : json.getString("scheduleId");
	}

	public String getDate() {
		return date;
	}
	
	public String getId() {
		return id;
	}

	public String getScheduleId() {
		return scheduleId;
	}
	
}
