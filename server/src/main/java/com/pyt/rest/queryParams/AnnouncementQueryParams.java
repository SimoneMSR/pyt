package com.pyt.rest.queryParams;

import Enums.Being;
import Enums.Department;

public class AnnouncementQueryParams extends BaseQueryParams{
	public Being[] createdBy;
	public Being[] targets;
	public Department[] departmentTargets;
	public String title;
	public String location;
	public String[] tags;
	
	public static final String ORDERBY_TITLE = "title";
	
	public String toString(){
		String out ="";
		return out;
	}
}
