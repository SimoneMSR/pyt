package com.pyt.rest.dto;


public class AnnouncementDto {
	
	public int id;
	public String title;
	public String description;
	
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AnnouncementDto");
        return sb.toString();
    }

}
