import { Injectable } from '@angular/core';
import {BaseService} from "../core";
import { Http, Response } from '@angular/http';
import {Observable} from "rxjs/Rx";
@Injectable()
export class LikeService extends BaseService{

  constructor(private http : Http) { 
  	super();
  	this.url = this.baseUrl + "/like";
  }


  public like(announcementId : number, dislike : boolean) : Observable<boolean>{
  		return this.http
  				.put(this.url + "?announcementId=" + announcementId + "&dislike=" + dislike,
  					{},
  					{headers : this.getHeaders()})
  				.map(res => <boolean>res.json());
  }

}
