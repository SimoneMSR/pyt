package com.pyt.util;

import javax.ws.rs.core.Response.Status;

public enum ErrorCollection {

	UnauthorizedException("Operation not permitted.", Status.FORBIDDEN, "http://todo"),

	ObjectLockException("Operation not permitted.", Status.FORBIDDEN, "http://todo"),

	LoginException("Login failed.", Status.FORBIDDEN, "http://todo"),

	EmailNotVerifyedException("Login failed.", Status.FORBIDDEN, "http://todo"),

	DatabaseException("Database exception.", Status.INTERNAL_SERVER_ERROR, "http://todo"),

	ConstraintViolation("Data violation.", Status.BAD_REQUEST, "http://todo"),

	DuplicateKeyException("Data duplicate.", Status.BAD_REQUEST, "http://todo"),

	NoConcurrencyCall("No concurrenty call permitted on this resource.", Status.CONFLICT, "http://todo"),

	UnknownError("Unknown error.", Status.INTERNAL_SERVER_ERROR, "http://todo"),

	ClientBadRequestException("Invalid request.", Status.BAD_REQUEST, "http://todo"),

	IntegrityRequestException("Invalid operation.", Status.BAD_REQUEST, "http://todo"),

	NotFoundException("Not found.", Status.NOT_FOUND, "http://todo");

	private final String desc;
	private final String url;
	private final Status status;

	ErrorCollection(final String desc, final Status status, final String url) {
		this.desc = desc;
		this.status = status;
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public String getUrl() {
		return url;
	}

	public Status getStatus() {
		return status;
	}

}
