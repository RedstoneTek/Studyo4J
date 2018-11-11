package com.tek.studyo.entities;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class Calendar {
	
	private String timezone;
	private List<SchoolDay> schoolDays;
	
	public Calendar(JSONObject json) {
		this.timezone = json.getString("timezone");
		this.schoolDays = JSONUtil.parseArrayIntoJSON(json.getJSONArray("schoolDays")).stream().map(jsonobj -> new SchoolDay(jsonobj)).collect(Collectors.toList());
	}
	
	public String getTimezone() {
		return timezone;
	}
	
	public Optional<SchoolDay> getSchoolDay(String date) {
		return schoolDays.stream().filter(schoolDay -> schoolDay.getDay().equals(date)).findFirst();
	}
	
	public List<SchoolDay> getSchoolDays() {
		return schoolDays;
	}
	
}
