package com.pyt.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pyt.rest.converter.QuarterConverter;
import com.pyt.rest.dto.QuarterDto;
import com.pyt.service.QuarterService;

@Path("quarter")
@Stateless
public class QuarterController extends BaseController{

	@Inject
	QuarterService quarterService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("")
	public List<QuarterDto> getAll(){
		return QuarterConverter.to(quarterService.getAll());
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("single")
	public QuarterDto getById(@QueryParam("quarterId") int id){
		return QuarterConverter.toComplete(quarterService.getById(id));
	}
	
}
