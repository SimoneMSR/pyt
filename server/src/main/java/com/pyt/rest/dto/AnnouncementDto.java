package com.pyt.rest.dto;

import java.util.Collection;

import Enums.AnnouncementCathegory;

public class AnnouncementDto {
	
	public int id;
	public String title;
	public String description;
	public AnnouncementCathegory cathegory; 
	public Collection<TagDto> tags;
	
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AnnouncementDto");
        return sb.toString();
    }

}
