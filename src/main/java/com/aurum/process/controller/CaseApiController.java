package com.aurum.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurum.process.dto.CaseLot;
import com.aurum.process.dto.Paths.Case;
import com.aurum.process.listener.CaseListener;

@RestController
@RequestMapping(value = Case.CASE_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class CaseApiController {

	@Autowired
	private CaseListener listener;

	@PostMapping
	public void create(@RequestBody CaseLot dto) {
		listener.sendCases(dto);
	}
}
