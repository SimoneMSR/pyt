import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import {Member} from './member.model';
import {BaseService} from '../core/base.service';

@Injectable()
export class LoginService  extends BaseService{

	private apiUrl : string;
  constructor() { 
  	super();
  	this.apiUrl = baseUrl + '/login';
  }

  login(credentials : Member){
  		this.http.post(apiUrl,credential);
  }

}
