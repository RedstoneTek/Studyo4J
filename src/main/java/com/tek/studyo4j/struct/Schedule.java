package com.tek.studyo4j.struct;

import java.util.List;

public class Schedule {
	
	private String id;
	private String title;
	private String tag;
	private List<Period> periods;
	private int colorIndex;
	private boolean isHidden;
	private boolean isNoSchoolSchedule;
	private List<String> specialDays;
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getTag() {
		return tag;
	}
	
	public List<Period> getPeriods() {
		return periods;
	}
	
	public int getColorIndex() {
		return colorIndex;
	}
	
	public boolean isHidden() {
		return isHidden;
	}
	
	public boolean isNoSchoolSchedule() {
		return isNoSchoolSchedule;
	}
	
	public List<String> getSpecialDays() {
		return specialDays;
	}
	
}
