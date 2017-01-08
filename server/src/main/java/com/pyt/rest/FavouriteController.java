package com.pyt.rest;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pyt.rest.authorization.KnockKnock;
import com.pyt.rest.converter.AnnouncementConverter;
import com.pyt.rest.dto.AnnouncementDto;
import com.pyt.service.FavouriteService;

@Stateless
@Path("favourite")
@KnockKnock
public class FavouriteController extends BaseController{
	
	@Inject
	private FavouriteService favouriteService;
	
	@POST
	public Response setFavourite(@QueryParam("announcementId") int announcementId){
		
		favouriteService.setFavourite(announcementId, getCurrentUser());
		return Response.ok().build();
	}
	
	@GET
	@Path("ids")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getMyFavouritesId(){
		return favouriteService.getFavouritesIds(getCurrentUser());
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<AnnouncementDto> getMyFavourites(){
		return AnnouncementConverter.to(favouriteService.getFavourites(getCurrentUser()));
	}

}
