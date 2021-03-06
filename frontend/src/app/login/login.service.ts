import { Injectable } from '@angular/core';
import {Login} from './login-form/login.model';
import {Member} from "../member/member.model";
import {BaseService} from '../core/base.service';
import {Observable, BehaviorSubject} from 'rxjs/Rx';
import { Http, Response, Headers, URLSearchParams } from '@angular/http';

@Injectable()
export class LoginService  extends BaseService{

	public user : Member;
	public userObservable : BehaviorSubject<Member>;
	public static readonly USERKEY = 'pyt-user';
	public static readonly EMAILKEY = 'pyt-email';
	public static readonly QUARTERKEY = "pyt-quarterId";
	public static readonly IDKEY = "pyt-id";
  constructor(private http : Http) { 
  	super();
  	this.url = this.baseUrl + '/login';
  	if(localStorage.getItem(LoginService.USERKEY) !=null){
  		this.user = new Member("","");
  		this.user.name=localStorage.getItem(LoginService.USERKEY);
  		this.user.email=localStorage.getItem(LoginService.EMAILKEY);
  		this.user.quarterId = Number(localStorage.getItem(LoginService.QUARTERKEY));
  		this.user.id = Number(localStorage.getItem(LoginService.QUARTERKEY));
  	}else
	  	this.user = null;
  	this.userObservable = new BehaviorSubject<Member>(this.user);
  }

  login(credentials : Login) : Observable<Response>{
  		return this.http.post(this.url,credentials,{headers: this.getHeaders()});
  }


  logout(){
  	localStorage.removeItem(LoginService.USERKEY);
  	localStorage.removeItem(LoginService.EMAILKEY);
  	localStorage.removeItem(LoginService.QUARTERKEY);
  	localStorage.removeItem(LoginService.IDKEY);
  	this.user=null;
  	this.userObservable.next(this.user);
  }

  setUserLoggedIn(user : Member){
  	this.user=user;
  	this.userObservable.next(this.user);
  	localStorage.setItem(LoginService.USERKEY,user.name);
  	localStorage.setItem(LoginService.EMAILKEY, user.email);
  	localStorage.setItem(LoginService.QUARTERKEY, user.quarterId.toString());
  	localStorage.setItem(LoginService.IDKEY, user.id.toString());
  }

  activate(email : string, hash : string) : Observable<Response>{
  	let body = new URLSearchParams();
    body.set("email",email);
    body.set("hash", hash);
  	return this.http.post(this.url + "/activate", body, {'headers' : new Headers({
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'})});
  }

}
