export class Member {
	public username : string;
	public name : string;
	public quarterId: number;
	public id : number;
	constructor (
		public email : string,
		public password : string
	){}
}