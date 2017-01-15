import { Injectable } from '@angular/core';
import {BaseService} from "../../core";
import { Observable} from 'rxjs/Rx';
import { Http, Response } from '@angular/http';
import {Message} from "../";

@Injectable()
export class MessagesService extends BaseService{


  constructor(private http : Http) {
  	super();
  	this.url = this.baseUrl + "/message";
  }

  public sendMessage(m : Message) : Observable<Response>{
  	return this.http
  		.post(this.url , m, {headers : this.getHeaders()});
  }

  public getMessagesOrdered() : Observable<Message[]>{
  		return this.http
  			.get(this.url + "/inbox", {headers : this.getHeaders()})
  			.map( res => <Message[]>res.json());
  }

  public countInbox() : Observable<number>{
  	return this.http
  		.get(this.url + "/inbox/count", {headers : this.getHeaders()})
  		.map(res => <number>res.json());
  }
}
