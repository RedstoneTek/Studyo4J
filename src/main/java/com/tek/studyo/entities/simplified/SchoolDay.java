package com.tek.studyo.entities.simplified;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tek.studyo.entities.IUser;
import com.tek.studyo.entities.School;
import com.tek.studyo.entities.Task;

public class SchoolDay {
	
	private String date;
	private List<String> specialDayTitles;
	private List<Course> courses;
	
	public SchoolDay(String date, School school, com.tek.studyo.entities.Calendar calendar, List<IUser> users, List<Task> tasks) {
		this.date = date;
		Optional<com.tek.studyo.entities.SchoolDay> schoolDay = calendar.getSchoolDay(date);
		if(schoolDay.isPresent()) {
			this.specialDayTitles = schoolDay.get().getSpecialDayTitles();
			this.courses = schoolDay.get().getPeriods().stream()
					.map(period -> new Course(date, period.getId(), school, calendar, users, tasks))
					.collect(Collectors.toList());
		}
	}

	public String getDate() {
		return date;
	}

	public List<String> getSpecialDayTitles() {
		return specialDayTitles;
	}

	public List<Course> getCourses() {
		return courses;
	}
	
}
