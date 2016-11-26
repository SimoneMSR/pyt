import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class BaseService {

	 protected baseUrl : string;
	 protected http;

  constructor() { 
  	this.http = Http;
  	this.baseUrl = 'http://localhotst:8080/pyt/rest';
  }

}
