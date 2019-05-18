package com.tek.studyo4j.struct;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Section {
	
	private String objectId;
	private boolean locked;
	private boolean hidden;
	private String title;
	private String gradeLevel;
	private String associatedSectionNumbers;
	private int colorIndex;
	private String defaultRoomName;
	private String managedIdentifier;
	private String defaultTeacherId;
	private List<SectionSchedule> schedules;
	
	public String getObjectId() {
		return objectId;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public boolean isHidden() {
		return hidden;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getGradeLevel() {
		return gradeLevel;
	}
	
	public String getAssociatedSectionNumbers() {
		return associatedSectionNumbers;
	}
	
	public int getColorIndex() {
		return colorIndex;
	}
	
	public String getDefaultRoomName() {
		return defaultRoomName;
	}
	
	public String getManagedIdentifier() {
		return managedIdentifier;
	}
	
	public String getDefaultTeacherId() {
		return defaultTeacherId;
	}
	
	public List<SectionSchedule> getSchedules() {
		return schedules;
	}
	
	public Optional<Teacher> getTeacher(List<IUser> users) {
		Optional<Teacher> teacherOpt = users.stream()
				.filter(user -> user.getRole().equals(Role.TEACHER))
				.map(user -> (Teacher)user)
				.filter(teacher -> teacher.getObjectId().equals(defaultTeacherId))
				.findFirst();
		return teacherOpt;
	}
	
	public List<Student> getEnrolledStudents(List<IUser> users) {
		return users.stream().filter(user -> user.getRole().equals(Role.STUDENT))
				.map(user -> (Student)user)
				.filter(student -> student.getSettings().getSelectedSectionIds().contains(objectId))
				.collect(Collectors.toList());
	}
	
}
