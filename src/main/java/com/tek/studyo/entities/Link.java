package com.tek.studyo.entities;

import org.json.JSONObject;

public class Link {
	
	private String text, url;

	public Link(JSONObject json) {
		this.text = json.getString("text");
		this.url = json.getString("url");
	}

	public String getText() {
		return text;
	}

	public String getUrl() {
		return url;
	}
	
}
