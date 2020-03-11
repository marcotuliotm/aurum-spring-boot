package com.aurum.process.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurum.process.domain.Case;
import com.aurum.process.dto.CaseDTO;
import com.aurum.process.dto.PagedResult;
import com.aurum.process.repository.CaseRepository;
import com.google.common.base.Strings;

@Service
public class CaseService extends AbstractEntityService<Case, CaseRepository> {
	private static final String SEARCH = "search";

	@Transactional
	public Long create(CaseDTO dto) {
		final Case caseNew = new Case(dto);
		return create(caseNew).getId();
	}

	public PagedResult<CaseDTO> findAll(String search, int page, int size) {
		final PageRequest pageRequest = PageRequest.of(
				page,
				size,
				Direction.DESC,
				SEARCH);

		final Page<Case> all = getCases(search, pageRequest);

		final List<CaseDTO> caseDTOS = all.getContent()
				.stream()
				.map(this::buildDTO)
				.collect(Collectors.toList());
		return new PagedResult<>(caseDTOS, all.getTotalPages(), page, size);
	}

	public CaseDTO getCase(Long id) {
		final Case caseById = findById(id);
		return buildDTO(caseById);
	}

	@Transactional
	public CaseDTO update(CaseDTO dto) {
		final Long id = dto.getId();
		final Case caseUpdate = findById(id);
		caseUpdate.update(dto);
		return buildDTO(update(caseUpdate));
	}

	@Transactional
	public void remove(Long id) {
		delete(id);
	}

	private Page<Case> getCases(String search, PageRequest pageRequest) {
		final Page<Case> all;
		if (Strings.isNullOrEmpty(search)) {
			all = repository.findAll(pageRequest);
		} else {
			all = repository.findBySearchGreaterThanEqual(search, pageRequest);
		}
		return all;
	}

	private CaseDTO buildDTO(Case aCase) {
		return CaseDTO.builder()
				.id(aCase.getId())
				.access(aCase.getAccess())
				.clients(aCase.getClients())
				.folder(aCase.getFolder())
				.tag(aCase.getTag())
				.title(aCase.getTitle())
				.responsible(aCase.getResponsible())
				.createdAt(aCase.getCreatedAt())
				.description(aCase.getDescription())
				.build();
	}
}
