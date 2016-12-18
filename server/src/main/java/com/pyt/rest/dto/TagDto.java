package com.pyt.rest.dto;

public class TagDto {
	public int id;
	public String name;
	
	public TagDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public TagDto(String name){
		this.name = name;	
	}
	
	public TagDto(){}
	
}
