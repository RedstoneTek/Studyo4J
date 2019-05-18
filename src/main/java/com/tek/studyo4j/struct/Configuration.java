package com.tek.studyo4j.struct;

import java.util.List;

public class Configuration {
	
	private String type;
	private String schoolName;
	private String title;
	private int version;
	private int daysPerCycle;
	private List<String> cycleDayTitles;
	private String startDate;
	private String endDate;
	private String language;
	private String managedOnboardCode;
	private boolean parentInvitesDisabled;
	private List<Schedule> schedules;
	private List<SpecialDay> specialDays;
	private List<Section> sections;
	private List<DayConfiguration> dayConfigurations;
	private boolean isManagedWithSubscriptions;
	private String managedSubscriptionCoupon;
	private List<Link> links;
	private boolean isDemo;
	private String comments;
	private String objectId;
	private String syncToken;
	
	public String getType() {
		return type;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getVersion() {
		return version;
	}
	
	public int getDaysPerCycle() {
		return daysPerCycle;
	}
	
	public List<String> getCycleDayTitles() {
		return cycleDayTitles;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getManagedOnboardCode() {
		return managedOnboardCode;
	}
	
	public boolean isParentInvitesDisabled() {
		return parentInvitesDisabled;
	}
	
	public List<Schedule> getSchedules() {
		return schedules;
	}
	
	public List<SpecialDay> getSpecialDays() {
		return specialDays;
	}
	
	public List<Section> getSections() {
		return sections;
	}
	
	public List<DayConfiguration> getDayConfigurations() {
		return dayConfigurations;
	}
	
	public boolean isManagedWithSubscriptions() {
		return isManagedWithSubscriptions;
	}
	
	public String getManagedSubscriptionCoupon() {
		return managedSubscriptionCoupon;
	}
	
	public List<Link> getLinks() {
		return links;
	}
	
	public boolean isDemo() {
		return isDemo;
	}
	
	public String getComments() {
		return comments;
	}
	
	public String getObjectId() {
		return objectId;
	}
	
	public String getSyncToken() {
		return syncToken;
	}
	
}
