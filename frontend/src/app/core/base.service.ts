import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

@Injectable()
export class BaseService {

	 protected baseUrl : string;
	 protected url : string;


  constructor() { 
    this.baseUrl = 'http://192.168.1.106:8080/pyt/rest'; //2.235.209.213
  	//this.baseUrl = 'http://2.235.209.213:8080/pyt/rest'; // 192.168.1.65:8080
  }

  protected getHeaders(){
    let headers = new Headers();
    headers.append('accept', 'application/json');
    return headers;
  }


}
