import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Login, Member} from './';
import {BaseService} from '../core/base.service';
import {Observable, BehaviorSubject} from 'rxjs/Rx';

@Injectable()
export class LoginService  extends BaseService{

	private apiUrl : string;
	public user : Member;
	public userObservable : BehaviorSubject<Member>;
  constructor(private http : Http) { 
  	super();
  	this.apiUrl = this.baseUrl + '/login';
  	if(localStorage.getItem('pyt-user') !=null){
  		this.user = new Member("","");
  		this.user.name=localStorage.getItem('pyt-user');
  	}else
	  	this.user = null;
  	this.userObservable = new BehaviorSubject<Member>(this.user);
  }

  login(credentials : Login) : Observable<Response>{
  		return this.http.post(this.apiUrl,credentials,{headers: this.getHeaders()});
  }

  logout(){
  	localStorage.removeItem('pyt-user');
  	this.user=null;
  	this.userObservable.next(this.user);
  }

  setUserLoggedIn(user : Member){
  	this.user=user;
  	localStorage.setItem('pyt-user',user.name);
  }

}
