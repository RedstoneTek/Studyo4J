package com.tek.studyo.entities.simplified;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tek.studyo.entities.IUser;
import com.tek.studyo.entities.Link;
import com.tek.studyo.entities.School;
import com.tek.studyo.entities.Task;

public class Calendar {
	
	private String timezone;
	private List<SchoolDay> schoolDays;
	private List<Link> links;
	private List<IUser> users;
	
	public Calendar(School school, com.tek.studyo.entities.Calendar calendar, List<IUser> users, List<Task> tasks) {
		this.timezone = calendar.getTimezone();
		this.schoolDays = calendar.getSchoolDays().stream()
			.map(schoolDay -> new SchoolDay(schoolDay.getDay(), school, calendar, users, tasks))
			.collect(Collectors.toList());
		this.links = school.getLinks();
		this.users = users;
	}

	public Optional<SchoolDay> getSchoolDay(String date) {
		return schoolDays.stream().filter(schoolDay -> schoolDay.getDate().equals(date)).findFirst();
	}
	
	public String getTimezone() {
		return timezone;
	}

	public List<SchoolDay> getSchoolDays() {
		return schoolDays;
	}

	public List<Link> getLinks() {
		return links;
	}
	
	public List<IUser> getUsers() {
		return users;
	}
	
}
