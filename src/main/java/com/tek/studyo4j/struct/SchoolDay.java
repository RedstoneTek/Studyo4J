package com.tek.studyo4j.struct;

import java.util.List;

public class SchoolDay {
	
	private String id;
	private String day;
	private int cycleDay;
	private String cycleDayTitle;
	private boolean isPadding;
	private List<String> specialDayTitles;
	private List<String> symbols;
	private List<Integer> symbolColorIndices;
	private int termNumber;
	private List<SchoolDayPeriod> periods;
	
	public String getId() {
		return id;
	}
	
	public String getDay() {
		return day;
	}
	
	public int getCycleDay() {
		return cycleDay;
	}
	
	public String getCycleDayTitle() {
		return cycleDayTitle;
	}
	
	public boolean isPadding() {
		return isPadding;
	}
	
	public List<String> getSpecialDayTitles() {
		return specialDayTitles;
	}
	
	public List<String> getSymbols() {
		return symbols;
	}
	
	public List<Integer> getSymbolColorIndices() {
		return symbolColorIndices;
	}
	
	public int getTermNumber() {
		return termNumber;
	}
	
	public List<SchoolDayPeriod> getPeriods() {
		return periods;
	}
	
}
