package com.tek.studyo.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtil {
	
	public static ArrayList<JSONObject> parseArrayIntoJSON(JSONArray array){
		ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		
		Iterator<Object> arrayIterator = array.iterator();
		while(arrayIterator.hasNext()) {
			jsonObjects.add((JSONObject)arrayIterator.next());
		}
		
		return jsonObjects;
	}
	
	public static ArrayList<String> parseArrayIntoString(JSONArray array){
		ArrayList<String> stringObjects = new ArrayList<String>();
		
		Iterator<Object> arrayIterator = array.iterator();
		while(arrayIterator.hasNext()) {
			stringObjects.add((String)arrayIterator.next());
		}
		
		return stringObjects;
	}
	
}
