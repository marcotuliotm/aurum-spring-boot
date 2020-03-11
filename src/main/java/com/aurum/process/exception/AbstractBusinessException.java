package com.aurum.process.exception;

import lombok.Getter;

@Getter
public abstract class AbstractBusinessException extends RuntimeException {
	private final String entityName;

	protected AbstractBusinessException(String entityName, String message) {
		super(message);
		this.entityName = entityName;
	}
}
