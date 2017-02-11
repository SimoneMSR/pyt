package com.pyt.rest.exception;

@SuppressWarnings("serial")
public class DuplicateKeyException extends ApplicationManagedException {



	public DuplicateKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateKeyException(String message) {
		super(message);
	}

	public DuplicateKeyException(Throwable cause) {
		super(cause);
	}

}
