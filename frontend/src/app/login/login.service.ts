import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Member} from './member.model';
import {BaseService} from '../core/base.service';

@Injectable()
export class LoginService  extends BaseService{

	private apiUrl : string;
  constructor(private http : Http) { 
  	super();
  	this.apiUrl = this.baseUrl + '/login';
  }

  login(credentials : Member){
  		this.http.post(this.apiUrl,credentials);
  }

}
