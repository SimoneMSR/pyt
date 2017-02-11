package com.pyt.rest.exception.mapper;



import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pyt.rest.exception.EmailNotVerifyedException;
import com.pyt.util.ErrorCollection;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class EmailNotVerifyedExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<EmailNotVerifyedException> {

	@Override
	public Response toResponse(EmailNotVerifyedException exception) {
		return formatResponse(ErrorCollection.EmailNotVerifyedException, exception.getMessage());
	}

}