package com.pyt.rest.exception;

@SuppressWarnings("serial")
public class NotFoundException extends ApplicationManagedException {

	public NotFoundException() {
		super("Resource not found");
	}


	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
