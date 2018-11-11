package com.tek.studyo.entities;

import org.json.JSONObject;

public class SpecialDay {
	
	private int cycleDay;
	private String symbol, id, title, scheduleId, cycleDayEffect;
	private boolean isEndOfTerm, isSymbolVisible, isTitleVisible;
	
	public SpecialDay(JSONObject json) {
		this.cycleDay = JSONObject.NULL.equals(json.get("cycleDay")) ? 0 : json.getInt("cycleDay");
		this.symbol = json.getString("symbol");
		this.id = json.getString("id");
		this.title = json.getString("title");
		this.scheduleId = !json.has("scheduleId") ? null : JSONObject.NULL.equals(json.get("scheduleId")) ? null : json.getString("scheduleId");
		this.cycleDayEffect = JSONObject.NULL.equals(json.get("cycleDayEffect")) ? null : json.getString("cycleDayEffect");
		this.isEndOfTerm = json.getBoolean("isEndOfTerm");
		this.isSymbolVisible = json.getBoolean("isSymbolVisible");
		this.isTitleVisible = json.getBoolean("isTitleVisible");
	}

	public int getCycleDay() {
		return cycleDay;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public String getCycleDayEffect() {
		return cycleDayEffect;
	}

	public boolean isEndOfTerm() {
		return isEndOfTerm;
	}

	public boolean isSymbolVisible() {
		return isSymbolVisible;
	}

	public boolean isTitleVisible() {
		return isTitleVisible;
	}
	
}
