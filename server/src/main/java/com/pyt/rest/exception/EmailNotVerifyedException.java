package com.pyt.rest.exception;

@SuppressWarnings("serial")
public class EmailNotVerifyedException extends ApplicationManagedException {

	public EmailNotVerifyedException(String message) {
		super(message);
	}

}
