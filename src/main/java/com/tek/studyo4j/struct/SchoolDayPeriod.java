package com.tek.studyo4j.struct;

import java.util.List;

public class SchoolDayPeriod {
	
	private String id;
	private boolean isCustomSchedule;
	private String scheduleTag;
	private String startTime;
	private String endTime;
	private String tag;
	private String kind;
	private List<CourseOccurrence> courseOccurrences;
	
	public String getId() {
		return id;
	}
	
	public boolean isCustomSchedule() {
		return isCustomSchedule;
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
	
	public List<CourseOccurrence> getCourseOccurrences() {
		return courseOccurrences;
	}
	
}
