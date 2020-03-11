package com.aurum.process.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurum.process.dto.CaseDTO;
import com.aurum.process.dto.PagedResult;
import com.aurum.process.dto.Paths.Case;
import com.aurum.process.service.CaseService;

@RestController
@RequestMapping(value = Case.CASE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CaseController {

	@Autowired
	private CaseService service;

	@GetMapping
	public PagedResult<CaseDTO> findAll( @RequestParam(value = "search", required = false) String search,
										 @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
										 @RequestParam(value = "size", defaultValue = "10") @Min(1) @Max(1000) int pageSize){
		return service.findAll(search, page, pageSize);
	}

	@GetMapping("/{id}")
	public CaseDTO getById(@PathVariable("id") long id){
		return service.getCase(id);
	}

	@PostMapping
	public Long create(@RequestBody CaseDTO dto) {
		return service.create(dto);
	}

	@PutMapping("/{id}")
	public CaseDTO update(@PathVariable("id") long id, @RequestBody CaseDTO dto) {
		dto.setId(id);
		return service.update(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") long id) {
		service.remove(id);
		return ResponseEntity.noContent()
				.build();
	}
}
