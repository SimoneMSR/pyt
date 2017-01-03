import { Injectable } from '@angular/core';
import {BaseService} from "../core";
import { Observable} from 'rxjs/Rx';
import { Http, Response } from '@angular/http';
import {CommentPyt} from "./";

@Injectable()
export class CommentsService extends BaseService {

  constructor(private http : Http) {
  	super();
  	this.url=this.baseUrl + '/comment';
  }

  public byAnnouncement(announcementId : number) : Observable<CommentPyt[]>{
  		return this.http
  			.get(this.url + "?announcementId=" + announcementId)
  			.map(res => <CommentPyt[]>res.json());
  }

  public postComment(announcementId : number, comment : CommentPyt) : Observable<boolean>{
  		return this.http
  			.put(this.url + "?announcementId=" + announcementId,comment, {headers: this.getHeaders()})
  			.map(res => <boolean>res.json());
  }

}
