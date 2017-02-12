package com.pyt.rest.exception.mapper;


import java.util.Set;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pyt.rest.dto.ErrorDto;
import com.pyt.util.ErrorCollection;
import com.pyt.util.PytMediaType;



public class BaseExceptionMapper {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	protected Response formatResponse(ErrorCollection errorType, String message) {
		return formatResponse(errorType, message, null);
	}

	protected Response formatResponse(ErrorCollection errorType, String message, Set<String> violation) {

		ErrorDto error = new ErrorDto();
		error.error = errorType.toString();
		error.url = errorType.getUrl();
		error.message = message;

		Status status = errorType.getStatus();
		return Response.status(status).entity(error).type(PytMediaType.JSON).build();

	}

}
