package com.aurum.process.exception;

public class UpdateNotAllowedException extends AbstractBusinessException {

	private static final String UPDATE_NOT_ALLOWED = "Update Not Allowed";

	public UpdateNotAllowedException(String entityName) {
		super(entityName, UPDATE_NOT_ALLOWED);
	}
}

