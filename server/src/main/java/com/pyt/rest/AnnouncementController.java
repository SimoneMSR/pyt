package com.pyt.rest;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pyt.rest.converter.AnnouncementConverter;
import com.pyt.rest.converter.TagConverter;
import com.pyt.rest.dto.AnnouncementDto;
import com.pyt.rest.dto.TagDto;
import com.pyt.rest.queryParams.AnnouncementParams;
import com.pyt.service.AnnouncementService;
import com.pyt.service.QuarterService;

import Enums.AnnouncementCathegory;

@Path("announcement")
@Stateless
public class AnnouncementController extends BaseController {
	
	@Inject
	QuarterService quarterService;
	
	@Inject
	AnnouncementService announcementService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<AnnouncementDto> getByQuarterId(@QueryParam("quarterId") Integer quarterId, 
			@QueryParam("top") Integer top, @QueryParam("skip") Integer skip, @QueryParam("filterBy") String filterBy,
			@QueryParam("orderBy") String orderBy){
		AnnouncementParams p = new AnnouncementParams(top,skip,filterBy,orderBy);
		return AnnouncementConverter.to(announcementService.getByQuaterId(quarterId,p));
	}
	
	@GET
	@Path("single")
	@Produces(MediaType.APPLICATION_JSON)
	public AnnouncementDto getById(@QueryParam("announcementId") int announcementId){
		return AnnouncementConverter.toComplete(announcementService.getById(announcementId));
	}
	
	@GET
	@Path("tags")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TagDto> getAll(){
		return TagConverter.to(announcementService.getAllTagsOrdered());
	}
	

	@POST
	@Path("publish")
    @Produces(MediaType.APPLICATION_JSON)
	public Response publishAnnouncement(@QueryParam("quarterId") int quarterId, @QueryParam("announcementId") int announcementId){
		try{
			quarterService.publishAnnouncement(quarterId, announcementId);
			return Response.ok().build();
		}catch(Exception e){
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response createOrUpdate(AnnouncementDto announcement){
		announcementService.createOrUpdate(AnnouncementConverter.from(announcement));
		return Response.ok().build();
	}
	
	@POST
	@Path("unpublish")
    @Produces(MediaType.APPLICATION_JSON)
	public Response unpublishAnnouncement(@QueryParam("quarterId") int quarterId,@QueryParam("announcementId") int announcementId){
		try{
			quarterService.unpublishAnnouncement(quarterId, announcementId);
			return Response.ok().build();
		}catch(Exception e){
			return Response.serverError().build();
		}
	}
}
