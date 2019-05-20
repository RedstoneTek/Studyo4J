package com.tek.studyo4j.struct;

import java.util.List;

public class CourseOccurrence {
	
	private String sectionId;
	private int ordinal;
	private String roomName;
	private String customTitle;
	private String customRoomName;
	private boolean skipped;
	private int normalizedOrdinal;
	private int termOrdinal;
	private int yearRemaining;
	private int termRemaining;
	private List<String> teacherIds;
	
	public String getSectionId() {
		return sectionId;
	}
	
	public int getOrdinal() {
		return ordinal;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public String getCustomTitle() {
		return customTitle;
	}
	
	public String getCustomRoomName() {
		return customRoomName;
	}
	
	public boolean isSkipped() {
		return skipped;
	}
	
	public int getNormalizedOrdinal() {
		return normalizedOrdinal;
	}
	
	public int getTermOrdinal() {
		return termOrdinal;
	}
	
	public int getYearRemaining() {
		return yearRemaining;
	}
	
	public int getTermRemaining() {
		return termRemaining;
	}
	
	public List<String> getTeacherIds() {
		return teacherIds;
	}
	
}
