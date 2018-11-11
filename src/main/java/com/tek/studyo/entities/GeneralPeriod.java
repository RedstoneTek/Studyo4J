package com.tek.studyo.entities;

import org.json.JSONObject;

public class GeneralPeriod {
	
	private String startTime, endTime, tag;
	
	public GeneralPeriod(JSONObject json) {
		this.startTime = json.getString("startTime");
		this.endTime = json.getString("endTime");
		this.tag = json.getString("tag");
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
	
}
