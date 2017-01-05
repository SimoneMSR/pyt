package com.pyt.rest.queryParams;


public class AnnouncementParams extends BaseParams{
	public String filterBy;
	public String orderBy;
	public String title;
	
	public AnnouncementParams(Integer top,Integer skip, 
			String filterBy, String orderBy,String title){
		this.top=top;
		this.skip = skip;
		this.filterBy = filterBy;
		this.orderBy = orderBy;
		this.title= title;
	}
	
	public String toString(){
		String out ="";
		return out;
	}
}
