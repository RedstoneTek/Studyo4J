package com.tek.studyo4j.struct;

public interface IUser {
	
	public Role getRole();
	public String getFirstName();
	public String getLastName();
	public String getEmail();
	public String getConfigId();
	public Settings getSettings();
	public String getObjectId();
	public String getSyncToken();
	public boolean isValid();
	
}
