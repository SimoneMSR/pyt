package com.pyt.rest.authorization;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.pyt.rest.BaseController;

@KnockKnock
@Interceptor
@Priority(200)
public class KnockKnockInterceptor {


	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {

		BaseController rest = (BaseController) context.getTarget();

		// AuthUserEntry authUserEntry =
		// loginCookieManager.getUserEntryFromCookies(rest.getHttpHeaders());
		AuthUserEntry authUserEntry = null;
		String email = rest.getHttpHeaders().getHeaderString("Authorization");
		if (email != null) 
			authUserEntry = new AuthUserEntry(email);
		rest.setAuthUserEntry(authUserEntry);
		return context.proceed();

	}
}
