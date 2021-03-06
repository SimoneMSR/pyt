package com.pyt.rest;

import com.pyt.model.Member;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pyt.rest.converter.MemberConverter;
import com.pyt.rest.dto.LoginDto;
import com.pyt.rest.dto.MemberDto;
import com.pyt.rest.exception.EmailNotVerifyedException;
import com.pyt.rest.exception.UnauthorizedException;
import com.pyt.service.MemberService;


@Path("login")
@Stateless
public class LoginController extends BaseController{

	@Inject
	MemberService memberService;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public MemberDto login(LoginDto credentials) throws EmailNotVerifyedException{
		Member user = memberService.findByEmail(credentials.email);
		if(user == null || !user.getPassword().equals(credentials.password))
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		if(!user.isVerified())
			throw new EmailNotVerifyedException("Email not verifyed.");
		return MemberConverter.to(user);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("activate")
	public MemberDto activateAccount(@NotNull @FormParam("email")String email,@NotNull @FormParam("hash") String hash) throws UnauthorizedException {
		Member member = memberService.activateAccount(email, hash);
		if(member != null)
			return MemberConverter.to(member);
		else
			return null;
	}
}
