package com.pyt.rest.exception.mapper;


import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pyt.rest.exception.UnauthorizedException;
import com.pyt.util.ErrorCollection;

/**
 * 
 * Viene lanciata un'eccezione non compatibile, è stata corretta un resteasy
 * 3.0.4.Final https://issues.jboss.org/browse/RESTEASY-937
 * 
 * @author dometec
 * 
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class UnauthorizedExceptionMapper extends BaseExceptionMapper implements ExceptionMapper<UnauthorizedException> {

	@Override
	public Response toResponse(UnauthorizedException exception) {
		return formatResponse(ErrorCollection.UnauthorizedException, exception.getMessage());
	}

}