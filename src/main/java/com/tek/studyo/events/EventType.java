package com.tek.studyo.events;

public enum EventType {
	
	LOGIN("Login"),
	SIGNUP("Signup"),
	LOGOUT("Logout"),
	
	GOTOTODAY("Goto today"),
	GOTOHEADERDAY("Goto header day"),
	CHANGEACTIVECONFIG("Change active config"),
	VIEWSCHOOLLINK("View school link"),
	VIEWGEARMENU("View gear menu"),
	
	VIEWPERIODINFO("View period info"),
	VIEWDAYINFO("View day info"),
	VIEWPERIODCONTENTS("View period contents"),
	VIEWDAYCONTENTS("View day contents"),
	
	VIEWTASK("View task"),
	ADDATTACHMENT("Add attachment"),
	REMOVEATTACHMENT("Remove attachment"),
	CHANGESECTION("Change section"),
	CHANGEICON("Change icon"),
	CHANGEPERIOD("Change period"),
	CHANGETITLE("Change title"),
	CHANGENOTES("Change notes"),
	TOGGLEIMPORTANT("Toggle important"),
	CHANGEANNOUNCEMENTDATE("Change announcement date"),
	CHANGEPLANNEDDATE("Change planned date"),
	CHANGEDUEDATE("Change due date"),
	ADDSTEP("Add step"),
	DELETESTEP("Delete step"),
	EDITSTEPDATE("Edit step date"),
	CREATETASK("Create task"),
	UPDATETASK("Update task"),
	CREATEWITHCAMERA("Create with camera"),
	MARKASDONE("Mark as done"),
	MARKASNOTDONE("Mark as not done"),
	DELETE("Delete"),
	COPY("Copy"),
	PASTE("Paste"),
	PUBLISHTOGROUP("Publish to group"),
	PUBLISHCUSTOM("Publish custom"),
	UNPUBLISH("Unpublish"),
	DISTRIBUTE("Distribute"),
	REPEAT("Repeat"),
	
	VIEWNOTE("View note"),
	UPDATENOTE("Update note"),
	
	QUICKMOVEPERIOD("Quick move period"),
	CUSTOMMOVEPERIOD("Custom move period"),
	EDITPERIOD("Edit period"),
	VIEWDASHBOARDDAYCONTENTS("View dashboard day contents"),
	VIEWTEACHERPERIODINFO("View teacher period info"),
	
	AUTOMATICSYNC("Automatic sync"),
	MANUALSYNC("Manual sync"),
	
	CLICKONGIFTBUTTON("Click on gift button"),
	CLICKONPLAN("Click on plan");
	
	private String eventString;
	
	EventType(String eventString){
		this.eventString = eventString;
	}
	
	public String getEventString() {
		return eventString;
	}
	
}
