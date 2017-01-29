package Enums;

public enum AnnouncementCathegory {
	IDEA("Idea",0),
	PROBLEM("Problem",1),
	PROPOSAL("Proposal",2);
	
	private final String text;
	public final int code;
	
	private AnnouncementCathegory(String text, int code){
		this.text=text;
		this.code = code;
	}
	
	
}
