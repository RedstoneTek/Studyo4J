package com.tek.studyo4j.struct;

import java.util.List;

public class SectionSchedule {
	
	private String id;
	private String scheduleTag;
	private int cycleDay;
	private String dayOfWeek;
	private String date;
	private String periodTag;
	private String roomName;
	private String startTime;
	private String endTime;
	private String displayPeriodTag;
	private String startDate;
	private String termTag;
	private List<String> teacherIds;
	
	public String getId() {
		return id;
	}
	
	public String getScheduleTag() {
		return scheduleTag;
	}
	
	public int getCycleDay() {
		return cycleDay;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getPeriodTag() {
		return periodTag;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public String getDisplayPeriodTag() {
		return displayPeriodTag;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getTermTag() {
		return termTag;
	}
	
	public List<String> getTeacherIds() {
		return teacherIds;
	}
	
}
