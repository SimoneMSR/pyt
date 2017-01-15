import { Injectable } from '@angular/core';
import {BaseService} from "../core";
import {Observable} from 'rxjs/Rx';
import { Http, Response } from '@angular/http';
import {Member} from "./";

@Injectable()
export class MemberService extends BaseService{

	private viewedMembers : { [key:number]:Member; };
	//public viewedMemberObservable : Observable<Member[]>;
  constructor(private http : Http) {
  	super();
  	this.url = this.baseUrl + "/member";
  	this.viewedMembers = {};
  }

  public getAll() : Observable<Member[]> {
  	return this.http
  		.get(this.url, {headers : this.getHeaders()})
  		.map(res => <Member[]>res.json());
  }

  public getSingle(memberId : number) : Observable<Member>{
  	if(this.viewedMembers[memberId]){
  		return  Observable.create(observable => {
  			observable.next(this.viewedMembers[memberId]);
  		});
  	}else{
  	return this.http
  		.get(this.url + "/single?id=" + memberId, {headers : this.getHeaders()} )
  		.map(res => <Member>res.json())
  		.do( member => {
  			this.viewedMembers[member.id]=member;
  		});
  	}
  }


}
