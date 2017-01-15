import {Member} from "../../member";
export class Message {
	public creatorId : number;
	public creator? : Member;
	public messageId : number;
	public subject : String;
	public object : String ;
	public recipients: number[];
	public date : Date;
}