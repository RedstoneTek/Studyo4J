package com.tek.studyo.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class SchoolDay {
	
	private String id, day, cycleDayTitle;
	private int cycleDay, termNumber;
	private boolean isPadding;
	private List<String> specialDayTitles;
	private List<Period> periods;
	
	public SchoolDay(JSONObject json) {
		this.id = json.getString("id");
		this.day = json.getString("day");
		this.cycleDayTitle = json.getString("cycleDayTitle");
		this.cycleDay = json.getInt("cycleDay");
		this.termNumber = json.getInt("termNumber");
		this.isPadding = json.getBoolean("isPadding");
		this.specialDayTitles = JSONUtil.parseArrayIntoString(json.getJSONArray("specialDayTitles"));
		this.periods = JSONUtil.parseArrayIntoJSON(json.getJSONArray("periods")).stream().map(jsonobj -> new Period(jsonobj)).collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public String getDay() {
		return day;
	}

	public String getCycleDayTitle() {
		return cycleDayTitle;
	}

	public int getCycleDay() {
		return cycleDay;
	}

	public int getTermNumber() {
		return termNumber;
	}

	public boolean isPadding() {
		return isPadding;
	}

	public List<String> getSpecialDayTitles() {
		return specialDayTitles;
	}

	public List<Period> getPeriods() {
		return periods;
	}
	
}
