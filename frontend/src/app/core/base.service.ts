import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import {LoginService} from '../login';

@Injectable()
export class BaseService {

	 protected baseUrl : string;
	 protected url : string;


  constructor() { 
    //this.baseUrl = 'http://192.168.1.106:8080/pyt/rest'; //2.235.209.213
  	//this.baseUrl = 'http://2.235.209.213:8080/pyt/rest'; // 192.168.1.65:8080
    this.baseUrl = 'http://localhost:8080/pyt/rest';
  }

  protected getHeaders(){
    let headers = this.getUnauthenticatedHeaders();
    headers.append('Authorization' ,localStorage.getItem(LoginService.EMAILKEY));
    return headers;
  }

  private getUnauthenticatedHeaders(){
    let headers = new Headers();
    headers.append('accept', 'application/json');
    //headers.append('Access-Control-Allow-Origin', '*');
    //headers.append('Access-Control-Allow-Headers', 'X-Requested-With');
    return headers;
  }


}
