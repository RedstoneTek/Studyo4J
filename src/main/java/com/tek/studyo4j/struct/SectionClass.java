package com.tek.studyo4j.struct;

public class SectionClass {
	
	private Section section;
	private String periodTag;
	
	public SectionClass(Section section, String periodTag) {
		this.section = section;
		this.periodTag = periodTag;
	}

	public Section getSection() {
		return section;
	}
	
	public String getPeriodTag() {
		return periodTag;
	}
	
}
