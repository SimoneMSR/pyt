package com.pyt.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.pyt.rest.authorization.KnockKnock;
import com.pyt.service.LikeService;

@KnockKnock
@Stateless
@Path("like")
public class LikeController extends BaseController {
	
	@Inject
	LikeService likeService;
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean like(@QueryParam("announcementId") int announcementId,@QueryParam("dislike") Boolean dislike){
		return likeService.like(getCurrentUser(),announcementId,dislike);
	}

}
