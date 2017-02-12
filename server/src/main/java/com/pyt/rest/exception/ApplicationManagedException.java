package com.pyt.rest.exception;

@SuppressWarnings("serial")
public class ApplicationManagedException extends Exception {



	public ApplicationManagedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationManagedException(String message) {
		super(message);
	}

	public ApplicationManagedException(Throwable cause) {
		super(cause);
	}

}
