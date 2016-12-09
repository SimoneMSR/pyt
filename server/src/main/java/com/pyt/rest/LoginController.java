package com.pyt.rest;

import com.pyt.model.Member;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.pyt.rest.converter.MemberConverter;
import com.pyt.rest.dto.LoginDto;
import com.pyt.rest.dto.MemberDto;
import com.pyt.service.MemberService;

@Path("login")
@Stateless
public class LoginController extends BaseController{

	@Inject
	MemberService memberService;
	
	@POST
	public MemberDto login(LoginDto credentials){
		Member user = memberService.findByEmail(credentials.email);
		if(user == null)
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		return MemberConverter.to(user);
	}
}
