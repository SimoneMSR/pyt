export class Announcement{
	public name : string;
	public description : string;
	public tags : Object[];

	constructor (name){
		this.name=name;
		this.description = "" + Math.random();
		this.tags = [{title: Math.random()},{title: Math.random()},{title: Math.random()}];
	}
}