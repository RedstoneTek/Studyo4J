package com.tek.studyo4j.struct;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public enum Role {
	
	STUDENT,
	TEACHER,
	PARENT;
	
	public static Optional<Role> getRole(String role) {
		return Arrays.stream(Role.values()).filter(roleEnum -> roleEnum.name().equalsIgnoreCase(role)).findFirst();
	}
	
	public static class RoleTypeAdapter extends TypeAdapter<Role> {
		@Override
		public void write(JsonWriter out, Role value) throws IOException {
			out.jsonValue(value.name());
		}

		@Override
		public Role read(JsonReader in) throws IOException {
			Optional<Role> roleOpt = Role.getRole(in.nextString());
			return roleOpt.isPresent() ? roleOpt.get() : null;
		} 
	}
	
}
