package com.aurum.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurum.process.dto.CaseLot;
import com.aurum.process.dto.Paths.Case;
import com.aurum.process.service.CaseService;

@RestController
@RequestMapping(value = Case.CASE_API, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class CaseApiController {

	@Autowired
	private CaseService service;

	@PostMapping
	public void create(@RequestBody CaseLot dto) {
		service.sendCases(dto);
	}
}
