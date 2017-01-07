package com.pyt.rest.dto;

import java.util.Collection;

import Enums.AnnouncementCathegory;

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
	
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AnnouncementDto");
        return sb.toString();
    }

}
