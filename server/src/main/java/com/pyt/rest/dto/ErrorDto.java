package com.pyt.rest.dto;


import java.io.Serializable;
import java.util.Set;


@SuppressWarnings("serial")
public class ErrorDto implements Serializable {

	public String error;
	public String message;
	public String desc;
	public String url;

	@Override
	public String toString() {
		String msg = "ErrorDto [error=" + error + ", message=" + message + ", desc=" + desc + ", url=" + url ;


		return msg;
	}

}
