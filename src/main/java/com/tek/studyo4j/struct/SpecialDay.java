package com.tek.studyo4j.struct;

import java.util.List;

public class SpecialDay {
	
	private String id;
	private int cycleDay;
	private String cycleDayEffect;
	private String symbol;
	private int symbolColorIndex;
	private String title;
	private boolean isEndOfTerm;
	private boolean isTitleVisible;
	private boolean isSymbolVisible;
	private List<String> scheduleIds;
	private String scheduleId;
	
	public String getId() {
		return id;
	}
	
	public int getCycleDay() {
		return cycleDay;
	}
	
	public String getCycleDayEffect() {
		return cycleDayEffect;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public int getSymbolColorIndex() {
		return symbolColorIndex;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean isEndOfTerm() {
		return isEndOfTerm;
	}
	
	public boolean isTitleVisible() {
		return isTitleVisible;
	}
	
	public boolean isSymbolVisible() {
		return isSymbolVisible;
	}
	
	public List<String> getScheduleIds() {
		return scheduleIds;
	}
	
	public String getScheduleId() {
		return scheduleId;
	}
	
}
