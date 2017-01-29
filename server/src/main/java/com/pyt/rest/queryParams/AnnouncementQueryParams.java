package com.pyt.rest.queryParams;


public class AnnouncementQueryParams extends BaseQueryParams{
	public String filterBy;

	public String title;
	
	public static final String ORDERBY_TITLE = "title";
	
	public AnnouncementQueryParams(Integer top,Integer skip, 
			String filterBy, String orderBy,String title){
		super(top,skip,orderBy);
		this.filterBy = filterBy;
		this.title= title;
	}
	
	public String toString(){
		String out ="";
		return out;
	}
}
