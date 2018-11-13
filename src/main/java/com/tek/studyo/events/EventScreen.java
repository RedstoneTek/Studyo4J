package com.tek.studyo.events;

public enum EventScreen {
	
	DAILYVIEW("Daily view"),
	WEEKLYVIEW("Weekly view"),
	MONTLYVIEW("Montly view"),
	YEARLYVIEW("Yearly view"),
	TEACHERPLANNERVIEW("Teacher planner view"),
	TIMELINEVIEW("Timeline view"),
	PERIODSVIEW("Periods view"),
	SCHOOLDASHBOARDVIEW("School dashboard view"),
	MASTERSCHEDULEVIEW("Master schedule view"),
	CONTENTDELETE("ContentDelete modal"),
	CONTENTPUBLISH("ContentPublish modal"),
	CONTENTPUBLISHSTUDENTSELECTION("ContentPublish student selection"),
	PREFERENCES("Preferences"),
	
	ONBOARDINGWELCOME("Onboarding welcome"),
	ONBOARDINGCREATEACCOUNT("Onboarding create account"),
	ONBOARDINGLOGIN("Onboarding login"),
	ONBOARDINGFORGOTPASSWORD("Onboarding forgot password"),
	ONBOARDINGCODEORCREATECONFIG("Onboarding code or create config"),
	ONBOARDINGENTERCODE("Onboarding enter code"),
	ONBOARDINGENTERFULLNAME("Onboarding enter full name"),
	ONBOARDINGAUTOMATCH("Onboarding automatch"),
	
	CONFIGWELCOME("Config welcome"),
	CONFIGSCHOOLINFO("Config school info"),
	CONFIGSCHEDULEEDIT("Config schedule edit"),
	CONFIGSCHEDULEEDITPERIOD("Config schedule edit period"),
	CONFIGSCHEDULELIST("Config schedule list"),
	CONFIGSPECIALDAYMENU("Config special day menu"),
	CONFIGSPECIALDAYLIST("Config special day list"),
	CONFIGSPECIALDAYEDIT("Config special day edit"),
	CONFIGCLASSMENU("Config class menu"),
	CONFIGCLASSTIMETABLE("Config class timetable"),
	CONFIGCLASSLIST("Config class list"),
	CONFIGCLASSEDIT("Config class edit"),
	
	SUBSCRIPTIONPLANS("Subscription plans"),
	SUBSCRIPTIONWELCOME("Subscription welcome"),
	
	ACCOUNTMANAGEMENT("Account management");
	
	private String screenString;
	
	EventScreen(String screenString){
		this.screenString = screenString;
	}
	
	public String getScreenString() {
		return screenString;
	}
	
}
