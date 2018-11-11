package com.tek.studyo.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class Schedule {
	
	private List<GeneralPeriod> periods;
	private String id, title;
	
	public Schedule(JSONObject json) {
		this.periods = JSONUtil.parseArrayIntoJSON(json.getJSONArray("periods")).stream().map(jsonobj -> new GeneralPeriod(jsonobj)).collect(Collectors.toList());
		this.id = json.getString("id");
		this.id = json.getString("title");
	}

	public List<GeneralPeriod> getPeriods() {
		return periods;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
}
