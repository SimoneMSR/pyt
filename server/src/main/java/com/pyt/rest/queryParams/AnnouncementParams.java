package com.pyt.rest.queryParams;

import Enums.AnnouncementCathegory;

public class AnnouncementParams extends BaseParams{
	public AnnouncementCathegory filterBy;
	public String orderBy;
	
	public AnnouncementParams(Integer top,Integer skip,AnnouncementCathegory filterBy, String orderBy){
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
