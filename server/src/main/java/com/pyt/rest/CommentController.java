package com.pyt.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pyt.model.Comment;
import com.pyt.rest.authorization.KnockKnock;
import com.pyt.rest.converter.CommentConverter;
import com.pyt.rest.dto.CommentDto;
import com.pyt.service.CommentService;

@KnockKnock
@Stateless
@Path("comment")
public class CommentController extends BaseController{

	@Inject
	CommentService commentService;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response postComment(@QueryParam("announcementId") int announcementId,CommentDto comment){
		Comment entity = CommentConverter.from(comment);
		if(commentService.postComment(announcementId, entity, getCurrentUser()))
			return Response.ok().build();
		else
			return  Response.serverError().build();
	}
	
}
