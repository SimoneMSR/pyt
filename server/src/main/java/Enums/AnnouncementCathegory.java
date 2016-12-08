package Enums;

public enum AnnouncementCathegory {
	IDEA("Idea"),
	PROPOSAL("Proposal"),
	PROBLEM("Problem");
	
	private final String text;
	
	private AnnouncementCathegory(String text){
		this.text=text;
	}
}
