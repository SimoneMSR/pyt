import {BaseQueryParam, Being, Department} from "../core";

export class AnnouncementQueryParam extends BaseQueryParam {
	public  createdBy : Being[];
	public  targets : Being[];
	public  departmentTargets : Department[];
	public title : string;
	public location : string ;
	public tags : string[];
}