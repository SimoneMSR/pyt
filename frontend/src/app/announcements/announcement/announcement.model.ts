export class Announcement{
	public name : string;
	public description : string;
	public tags : Object[];
	public cathegory : string;

	constructor (name){
		this.name=name;
		this.description = "" + Math.random();
		this.tags = [{title: Math.random()},{title: Math.random()},{title: Math.random()}];
		this.cathegory = Math.random()<1/3 ? 'idea' : Math.random()<1/3 ? 'problem' : 'proposal';
	}
}