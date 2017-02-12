package com.pyt.rest.exception;

import javax.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "UnauthorizedException")
public class UnauthorizedException extends ApplicationManagedException {



	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

}
