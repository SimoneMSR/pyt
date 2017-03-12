package Enums;

public enum Being {
	
	STUDENT(1), STARTUP(2), COMPANY(3), PA(4), OTHER(5);
	
	public int code;
	
	

	private Being(int c){
		this.code=c;
	}
}
