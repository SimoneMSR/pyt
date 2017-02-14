package com.pyt.rest.dto;

import java.util.Collection;

import Enums.AnnouncementCathegory;
import Enums.Being;
import Enums.Department;

public class AnnouncementDto {
	
	public int id;
	public String title;
	public String description;
	public AnnouncementCathegory cathegory; 
	public Collection<TagDto> tags;
	public Collection<Integer> quarters;
	public int likes;
	public int dislikes;
	public MemberDto creator;
	public Collection<Being> targets;
	public Collection<Department> departmentTargets;
	public String location;
	
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AnnouncementDto");
        return sb.toString();
    }

}
