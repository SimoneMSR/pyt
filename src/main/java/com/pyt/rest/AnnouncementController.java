package com.pyt.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pyt.rest.converter.AnnouncementConverter;
import com.pyt.rest.dto.AnnouncementDto;
import com.pyt.service.AnnouncementService;
import com.pyt.service.QuarterService;

@Path("announcement")
@Stateless
public class AnnouncementController extends BaseController {
	
	@Inject
	QuarterService quarterService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response publishAnnouncement(@QueryParam("quarterId") int quarterId, AnnouncementDto announcement){
		try{
			quarterService.publishAnnouncement(quarterId, AnnouncementConverter.from(announcement));
			return Response.ok().build();
		}catch(Exception e){
			return Response.serverError().build();
		}
		
	}
}
