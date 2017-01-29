package com.pyt.rest.queryParams;

public class BaseQueryParams {
	public Integer top;
	public Integer skip;
	public String orderBy;
	
	public static final String TOP_TITLE = "top";
	public static final String SKIP_TITLE = "skip";
	public static final String ORERBY_TITLE = "orderBy";
	
	public BaseQueryParams(Integer top, Integer skip, String orderBy){
		this.top=top;
		this.skip = skip;
		this.orderBy = orderBy;
	}
	public BaseQueryParams(String top, String skip, String orderBy){
		if(top !=null)
			this.top = Integer.parseInt(top);
		if(skip !=null)
			this.top = Integer.parseInt(skip);
		this.orderBy=orderBy;
	}
}
