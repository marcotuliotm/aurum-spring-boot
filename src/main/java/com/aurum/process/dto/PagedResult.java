package com.aurum.process.dto;

import java.util.List;

import lombok.Data;


@Data
public class PagedResult<T> {
	private int offset;
	private int limit;
	private long totalElements;
	private List<T> elements;
	private int totalPages;

	public PagedResult(List<T> elements, int totalPages, long totalElements, int offset, int limit) {
		this.elements = elements;
		this.totalPages = totalPages;
		this.offset = offset;
		this.limit = limit;
		this.totalElements = totalElements;
	}

	public boolean hasMore() {
		return totalElements > offset + limit;
	}

	public boolean hasPrevious() {
		return offset > 0 && totalElements > 0;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public List<T> getElements() {
		return elements;
	}
}
