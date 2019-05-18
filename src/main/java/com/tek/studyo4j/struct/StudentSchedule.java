package com.tek.studyo4j.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentSchedule {
	
	private Map<Integer, List<SectionClass>> classSchedule;
	
	public StudentSchedule(Configuration configuration, Student student) {
		this.classSchedule = new HashMap<Integer, List<SectionClass>>();
		loadClasses(configuration, student);
	}
	
	public void loadClasses(Configuration configuration, Student student) {
		List<Section> enrolledSections = student.getSettings().getSelectedSectionIds().stream()
				.map(id -> configuration.getSections().stream().filter(section -> section.getObjectId().equals(id)).findFirst())
				.filter(Optional<Section>::isPresent)
				.map(Optional<Section>::get)
				.collect(Collectors.toList());
		
		for(int cycleDay = 1; cycleDay <= configuration.getDaysPerCycle(); cycleDay++) {
			List<SectionClass> classList = new ArrayList<SectionClass>();
			
			for(Section section : enrolledSections) {
				for(SectionSchedule schedule : section.getSchedules()) {
					if(schedule.getCycleDay() == cycleDay) {
						Schedule firstSchedule = configuration.getSchedules().get(0);
						Optional<Period> periodOpt = firstSchedule.getPeriods().stream().filter(period -> period.getTag().equals(schedule.getPeriodTag())).findFirst();
						
						if(periodOpt.isPresent()) {
							int index = 0;
							for(SectionClass otherSectionClass : classList) {
								Optional<Period> otherPeriodOpt = firstSchedule.getPeriods().stream().filter(period -> period.getTag().equals(otherSectionClass.getPeriodTag())).findFirst();
								if(otherPeriodOpt.isPresent()) {
									int startTime = formattedTimeToMinutes(periodOpt.get().getStartTime());
									int otherStartTime = formattedTimeToMinutes(otherPeriodOpt.get().getStartTime());
								
									if(startTime < otherStartTime) break;
								}
								
								index++;
							}
							
							SectionClass sectionClass = new SectionClass(section, schedule.getPeriodTag());
							classList.add(index, sectionClass);
						}
					}
				}
			}
			
			classSchedule.put(cycleDay, classList);
		}
	}
	
	public Map<Integer, List<SectionClass>> getClassSchedule() {
		return classSchedule;
	}
	
	public List<SectionClass> getClasses(int cycleDay) {
		return classSchedule.get(cycleDay);
	}
	
	public int formattedTimeToMinutes(String time) {
		String[] parts = time.split(":");
		return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
	}
	
}
