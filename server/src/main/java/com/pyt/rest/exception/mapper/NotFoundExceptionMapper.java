package com.pyt.rest.exception.mapper;


import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pyt.rest.exception.NotFoundException;
import com.pyt.util.ErrorCollection;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class NotFoundExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		return formatResponse(ErrorCollection.NotFoundException, exception.getMessage());
	}

}