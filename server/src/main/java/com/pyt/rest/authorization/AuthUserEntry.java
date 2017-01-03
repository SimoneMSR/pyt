package com.pyt.rest.authorization;

import java.io.Serializable;

public class AuthUserEntry implements  Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public String name;
	public String email;
	
	public AuthUserEntry(String email){
		this.email=email;
	}

}
