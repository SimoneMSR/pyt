import { Tag} from './tag.model';

export class Announcement{
	public title : string;
	public description : string;
	public tags : Tag[];
	public cathegory : string;
	public quarterIds : number[];

	public static extractTags(tags : Tag[]){
		let retval = [];
		for(let t of tags)
			retval.push(t.name);
		return retval;
	}
}