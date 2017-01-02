import {Announcement} from "../announcements";
export class Quarter {
	public name : String;
	public id : number;
	public announcements : Announcement[];

	public Quarter(id : number){
		this.id = id;
	}
}