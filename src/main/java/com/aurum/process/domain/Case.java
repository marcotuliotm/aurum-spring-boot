package com.aurum.process.domain;


import static com.google.common.base.Strings.emptyToNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

import com.aurum.process.dto.Access;
import com.aurum.process.dto.CaseDTO;
import com.google.common.base.Strings;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "cases")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Case extends AbstractEntity {

	@Size(max = 40)
	private String folder;

	@NotEmpty
	private String clients;

	@NotEmpty
	private String title;

	private List<String> tag;

	private String description;

	@NotEmpty
	private String responsible;

	@NotEmpty
	private Access access;

	@NotNull
	private Instant createdAt;

	private List<String> search;

	public Case(CaseDTO dto) {
		this.createdAt = Instant.now();
		update(dto);
	}

	public void update(CaseDTO dto) {
		this.folder = dto.getFolder();
		this.clients = dto.getClients();
		this.title = dto.getTitle();
		this.tag = dto.getTag();
		this.description = dto.getDescription();
		this.responsible = dto.getResponsible();
		this.access = dto.getAccess();

		final List<String> strings = new ArrayList<>();
		addTokens(strings, dto.getFolder());
		addTokens(strings, dto.getTitle());
		addTokens(strings, dto.getDescription());
		this.search = strings;
	}

	public void addTokens(List<String> strings, String folder) {
		if (!Strings.isNullOrEmpty(folder)) {
			strings.add(folder);
		}
	}
}
