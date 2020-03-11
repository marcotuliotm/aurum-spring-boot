package com.aurum.process.dto;

public interface Paths {
	String APP = "/app";
	String API = "/api";

	interface Case {
		String CASE = APP + "/cases";
		String CASE_API = API + "/cases";
	}
}
