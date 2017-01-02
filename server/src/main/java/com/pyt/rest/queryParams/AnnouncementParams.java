package com.pyt.rest.queryParams;


public class AnnouncementParams extends BaseParams{
	public String filterBy;
	public String orderBy;
	
	public AnnouncementParams(Integer top,Integer skip, String filterBy, String orderBy){
		this.top=top;
		this.skip = skip;
		this.filterBy = filterBy;
		this.orderBy = orderBy;
	}
	
	public String toString(){
		String out ="";
		return out;
	}
}
