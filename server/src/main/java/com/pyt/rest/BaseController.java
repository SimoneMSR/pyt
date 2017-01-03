package com.pyt.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pyt.dao.MemberDao;
import com.pyt.model.Member;
import com.pyt.rest.authorization.AuthUserEntry;

public class BaseController {

	public static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	@Context
	private HttpHeaders httpheaders;
	
	@Inject
	private MemberDao memberDao;

	private AuthUserEntry authUserEntry;

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public AuthUserEntry getCurrentAuthUser() {
		return authUserEntry;
	}
	
	public HttpHeaders getHttpHeaders() {
		return httpheaders;
	}
	
	public void setAuthUserEntry(AuthUserEntry authUserEntry) {
		this.authUserEntry = authUserEntry;
	}
	
	public Member getCurrentUser() {
		if (authUserEntry == null)
			return null;
		return memberDao.getByEmail(authUserEntry.email);
	}
}
