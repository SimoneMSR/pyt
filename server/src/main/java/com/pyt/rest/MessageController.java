package com.pyt.rest;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.pyt.rest.authorization.KnockKnock;
import com.pyt.rest.converter.ConversationConverter;
import com.pyt.rest.converter.MessageConverter;
import com.pyt.rest.dto.ConversationDto;
import com.pyt.rest.dto.MessageDto;
import com.pyt.rest.queryParams.BaseQueryParams;
import com.pyt.service.MessageService;

@Stateless
@Path("message")
@KnockKnock
public class MessageController extends BaseController{
	
	@Inject
	private MessageService service;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendMessage(MessageDto message){
		service.sendMessage(message, getCurrentUser());
		return Response.ok().build();
	}
	
	@GET
	@Path("inbox")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MessageDto> getInbox(){
		return MessageConverter.to(service.getInboxOrderebByReceiverId(getCurrentUser().getId().intValue()));
	}
	
	@GET
	@Path("inbox/count")
	@Produces(MediaType.APPLICATION_JSON)
	public int countInbox(){
		return service.inboxCount(getCurrentUser().getId().intValue());
	}
	
	@GET
	@Path("conversation")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<ConversationDto> getConversations(@Context UriInfo ui){
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		BaseQueryParams params = new BaseQueryParams(
				queryParams.getFirst(BaseQueryParams.TOP_TITLE),
				queryParams.getFirst(BaseQueryParams.SKIP_TITLE),
				queryParams.getFirst(BaseQueryParams.ORERBY_TITLE)
				); 
		return ConversationConverter.to(service.getConversationByMemberId(getCurrentUser().getId().intValue(), params));
	}
	

}
