package com.pyt.rest;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.pyt.model.Announcement;
import com.pyt.model.Announcement_;
import com.pyt.rest.authorization.KnockKnock;
import com.pyt.rest.converter.AnnouncementConverter;
import com.pyt.rest.converter.TagConverter;
import com.pyt.rest.dto.AnnouncementDto;
import com.pyt.rest.dto.TagDto;
import com.pyt.rest.queryParams.AnnouncementParams;
import com.pyt.service.AnnouncementService;
import com.pyt.service.QuarterService;

@Stateless
@Path("announcement")
@KnockKnock
public class AnnouncementController extends BaseController {
	
	@Inject
	private QuarterService quarterService;
	
	@Inject
	private AnnouncementService announcementService;
	
	@Inject
	private Logger log;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<AnnouncementDto> getByQuarterId(@QueryParam("quarterId") Integer quarterId, 
			@QueryParam("top") Integer top, 
			@QueryParam("skip") Integer skip, 
			@QueryParam("filterBy") String filterBy,
			@QueryParam("orderBy") String orderBy,
			@QueryParam("title") String title){
		AnnouncementParams p = new AnnouncementParams(top,skip,filterBy,orderBy,title);
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
		Announcement entity = AnnouncementConverter.from(announcement);
		if(announcement.id==0)
			entity.setCreator(getCurrentUser());
		else{
			Announcement currentEntity = announcementService.getById(announcement.id,Announcement_.creator);
			announcementService.checkAnnouncementOwnership(currentEntity,getCurrentUser());
			currentEntity.setTitle(entity.getTitle());
			currentEntity.setCathegory(entity.getCathegory());
			currentEntity.setDescription(entity.getDescription());
			currentEntity.setQuarters(entity.getQuarters());
			currentEntity.setTags(entity.getTags());
			entity=currentEntity;
		}
		announcementService.createOrUpdate(entity);
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
