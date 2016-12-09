import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

@Injectable()
export class BaseService {

	 protected baseUrl : string;
	 protected url : string;


  constructor() { 
  	this.baseUrl = 'http://localhost:8080/pyt/rest';
  }

  protected getHeaders(){
    let headers = new Headers();
    headers.append('accept', 'application/json');
    return headers;
  }


}
