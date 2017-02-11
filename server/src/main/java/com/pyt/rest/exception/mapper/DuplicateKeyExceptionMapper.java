package com.pyt.rest.exception.mapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pyt.rest.exception.DuplicateKeyException;
import com.pyt.util.ErrorCollection;



@Provider
@Produces(MediaType.APPLICATION_JSON)
public class DuplicateKeyExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<DuplicateKeyException> {

	@Override
	public Response toResponse(DuplicateKeyException exception) {
		return formatResponse(ErrorCollection.DuplicateKeyException, exception.getMessage());
	}

}