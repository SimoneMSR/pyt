import { Injectable } from '@angular/core';


@Injectable()
export class BaseService {

	 protected baseUrl : string;

  constructor(protected http : Http) { 
  	this.baseUrl = 'http://localhotst:8080/pyt/rest';
  }

}
