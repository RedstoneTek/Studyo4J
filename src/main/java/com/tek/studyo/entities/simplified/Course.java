package com.tek.studyo.entities.simplified;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tek.studyo.entities.IUser;
import com.tek.studyo.entities.Period;
import com.tek.studyo.entities.School;
import com.tek.studyo.entities.Task;

public class Course {
	
	private String id, date, startTime, endTime, kind;
	private Subject subject;
	private List<Task> tasks;
	
	public Course(String date, String id, School school, com.tek.studyo.entities.Calendar calendar, List<IUser> users, List<Task> tasks) {
		this.id = id;
		Optional<com.tek.studyo.entities.SchoolDay> schoolDayOpt = calendar.getSchoolDay(date);
		if(schoolDayOpt.isPresent()) {
			com.tek.studyo.entities.SchoolDay day = schoolDayOpt.get();
			this.date = day.getDay();
			Optional<Period> periodOpt = day.getPeriods().stream()
					.filter(period -> period.getId().equals(id))
					.findFirst();
			if(periodOpt.isPresent()) {
				Period period = periodOpt.get();
				this.startTime = period.getStartTime();
				this.endTime = period.getEndTime();
				this.kind = period.getKind();
				if(period.getCourseOccurrences().size() == 1) {
					com.tek.studyo.entities.Course course = period.getCourseOccurrences().get(0);
					this.subject = new Subject(course, course.getSectionId(), school, calendar, users);
					this.tasks = tasks.stream()
							.filter(task -> task.getDueDate().equals(date))
							.filter(task -> task.getSectionId() != null)
							.filter(task -> task.getSectionId().equals(course.getSectionId()))
							.collect(Collectors.toList());
				}
			}
		}
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getKind() {
		return kind;
	}

	public Subject getSubject() {
		return subject;
	}

	public List<Task> getTasks() {
		return tasks;
	}
	
}
