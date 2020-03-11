package com.aurum.process.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseDTO {

	private Long id;

	private String folder;

	private String clients;

	private String title;

	private List<String> tag;

	private String description;

	private String responsible;

	private Access access;

	private Instant createdAt;
}
