package com.tek.studyo.entities.simplified;

import java.util.List;
import java.util.Optional;

import com.tek.studyo.Studyo;
import com.tek.studyo.entities.IUser;
import com.tek.studyo.entities.School;
import com.tek.studyo.entities.Section;
import com.tek.studyo.entities.Teacher;

public class Subject {
	
	private String id, title, room;
	private Teacher teacher;
	
	public Subject(com.tek.studyo.entities.Course course, String id, School school, com.tek.studyo.entities.Calendar calendar, List<IUser> users) {
		this.id = id;
		Optional<Section> sectionAssociated = Studyo.getSectionByTeacherId(school, course.getTeacherIds().get(0));
		if(sectionAssociated.isPresent()) {
			Section section = sectionAssociated.get();
			this.title = section.getTitle();
			this.room = section.getDefaultRoomName();
			Optional<Teacher> teacherOpt = Studyo.getTeacherById(users, section.getDefaultTeacherId());
			if(teacherOpt.isPresent()) {
				this.teacher = teacherOpt.get();
			}
		}
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getRoom() {
		return room;
	}

	public Teacher getTeacher() {
		return teacher;
	}
	
}
