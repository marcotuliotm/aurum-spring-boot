package com.aurum.process.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.aurum.process.domain.Case;
import com.aurum.process.dto.Access;
import com.aurum.process.dto.CaseDTO;
import com.aurum.process.dto.PagedResult;
import com.aurum.process.repository.CaseRepository;

public class CaseServiceTest {

	private CaseDTO caseDTO;

	@InjectMocks
	private CaseService service = new CaseService();

	@Mock
	private CaseRepository repository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		caseDTO = CaseDTO.builder()
				.access(Access.PUBLIC)
				.clients("Joao")
				.folder("Home")
				.tag(Arrays.asList("Band", "Fight"))
				.title("Dr")
				.responsible("Maria")
				.description("Description")
				.build();
		final List<Case> cases = new ArrayList<>();
		LongStream.range(1, 8L).forEach(i -> {
			final Case create = new Case(caseDTO);
			create.setId(i);
			cases.add(create);
		});
		when(repository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(cases));

		cases.clear();

		LongStream.range(1, 5L).forEach(i -> {
			final Case create = new Case(caseDTO);
			create.setId(i);
			cases.add(create);
		});
		when(repository.findBySearchEquals(eq("search"), any(PageRequest.class))).thenReturn(new PageImpl<>(cases));
		final Case create = new Case(caseDTO);
		create.setId(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(create));
	}

	@Test
	void shouldBeCreate() {
		final Case create = new Case(caseDTO);
		create.setId(1L);
		when(repository.save(any())).thenReturn(create);
		assertEquals(create.getId(), service.create(caseDTO));
	}

	@Test
	void shouldBeWithoutSearch() {
		final PagedResult<CaseDTO> all = service.findAll(null, 0, 10);
		assertEquals(all.getTotalElements(), 7L);
	}

	@Test
	void shouldBeWithSearch() {
		final PagedResult<CaseDTO> all = service.findAll("search", 0, 10);
		assertEquals(all.getTotalElements(), 4L);
	}

	@Test
	void shouldBeGetCase() {
		assertEquals(1L, service.getCase(1L).getId());
	}

	@Test
	void shouldBeUpdate() {
		caseDTO.setId(1L);
		final Case create = new Case(caseDTO);
		create.setId(1L);
		when(repository.save(any())).thenReturn(create);
		final CaseDTO update = service.update(caseDTO);
		assertEquals(create.getId(), update.getId());
	}

	@Test
	void shouldBeNoSuchElementException() {
		final Case create = new Case(caseDTO);
		create.setId(1L);
		when(repository.save(any())).thenReturn(create);
		Assertions.assertThrows(NoSuchElementException.class, () -> service.update(caseDTO));
	}
}