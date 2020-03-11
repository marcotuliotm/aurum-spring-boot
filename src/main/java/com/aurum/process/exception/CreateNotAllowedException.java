package com.aurum.process.exception;

public class CreateNotAllowedException extends AbstractBusinessException {

	private static final String CREATE_NOT_ALLOWED = "Create Not Allowed";

	public CreateNotAllowedException(String entityName) {
		super(entityName, CREATE_NOT_ALLOWED);
	}
}
