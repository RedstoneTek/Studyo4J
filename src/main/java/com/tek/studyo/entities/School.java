package com.tek.studyo.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.tek.studyo.util.JSONUtil;

public class School {
	
	private String syncToken, startDate, endDate, language, type, title, objectId, managedOnboardCode;
	private int daysPerCycle;
	private List<String> cycleDayTitles;
	private List<SpecialDay> specialDays;
	private List<Schedule> schedules;
	private List<Section> sections;
	private List<DayConfiguration> dayConfigurations;
	private List<Link> links;
	
	public School(JSONObject json) {
		this.syncToken = json.getString("syncToken");
		this.startDate = json.getString("startDate");
		this.endDate = json.getString("endDate");
		this.language = json.getString("language");
		this.type = json.getString("type");
		this.title = json.getString("title");
		this.objectId = json.getString("objectId");
		this.managedOnboardCode = json.getString("managedOnboardCode");
		this.daysPerCycle = json.getInt("daysPerCycle");
		this.cycleDayTitles = JSONUtil.parseArrayIntoString(json.getJSONArray("cycleDayTitles"));
		this.specialDays = JSONUtil.parseArrayIntoJSON(json.getJSONArray("specialDays")).stream().map(jsonobj -> new SpecialDay(jsonobj)).collect(Collectors.toList());
		this.schedules = JSONUtil.parseArrayIntoJSON(json.getJSONArray("schedules")).stream().map(jsonobj -> new Schedule(jsonobj)).collect(Collectors.toList());
		this.sections = JSONUtil.parseArrayIntoJSON(json.getJSONArray("sections")).stream().map(jsonobj -> new Section(jsonobj)).collect(Collectors.toList());
		this.dayConfigurations = JSONUtil.parseArrayIntoJSON(json.getJSONArray("dayConfigurations")).stream().map(jsonobj -> new DayConfiguration(jsonobj)).collect(Collectors.toList());
		this.links = JSONUtil.parseArrayIntoJSON(json.getJSONArray("links")).stream().map(jsonobj -> new Link(jsonobj)).collect(Collectors.toList());
	}

	public String getSyncToken() {
		return syncToken;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getLanguage() {
		return language;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getManagedOnboardCode() {
		return managedOnboardCode;
	}

	public int getDaysPerCycle() {
		return daysPerCycle;
	}

	public List<String> getCycleDayTitles() {
		return cycleDayTitles;
	}

	public List<SpecialDay> getSpecialDays() {
		return specialDays;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public List<Section> getSections() {
		return sections;
	}

	public List<DayConfiguration> getDayConfigurations() {
		return dayConfigurations;
	}

	public List<Link> getLinks() {
		return links;
	}
	
}
