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
	public static readonly USERKEY = 'pyt-user';
	public static readonly EMAILKEY = 'pyt-email';
  constructor(private http : Http) { 
  	super();
  	this.apiUrl = this.baseUrl + '/login';
  	if(localStorage.getItem(LoginService.USERKEY) !=null){
  		this.user = new Member("","");
  		this.user.name=localStorage.getItem(LoginService.USERKEY);
  	}else
	  	this.user = null;
  	this.userObservable = new BehaviorSubject<Member>(this.user);
  }

  login(credentials : Login) : Observable<Response>{
  		return this.http.post(this.apiUrl,credentials,{headers: this.getHeaders()});
  }

  logout(){
  	localStorage.removeItem(LoginService.USERKEY);
  	localStorage.removeItem(LoginService.EMAILKEY);
  	this.user=null;
  	this.userObservable.next(this.user);
  }

  setUserLoggedIn(user : Member){
  	this.user=user;
  	this.userObservable.next(this.user);
  	localStorage.setItem(LoginService.USERKEY,user.name);
  	localStorage.setItem(LoginService.EMAILKEY, user.email);
  }

}
