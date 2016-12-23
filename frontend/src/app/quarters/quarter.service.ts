import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {BaseService} from '../core';
import {Quarter} from './';

@Injectable()
export class QuarterService  extends BaseService{

	public Quarters : Quarter[];
	public QuarterIdMap : {[key:string]:Quarter};
  constructor(private http: Http) { 
  	super();
  	this.url = "quarter";
  	this.refresh();
  	}


   public getAll() : Observable<Quarter[]> {
  		return this.http
  		      .get(this.baseUrl+"/"+this.url, {headers: this.getHeaders()})
     		  .map(res => <Quarter[]>res.json());

  	}

  	private refresh(){
  		this.getAll()
	  		.subscribe( res => {
	  			this.Quarters = res;
	  			this.buildQuarterIdMap();
  		});
  	}

  	private buildQuarterIdMap(){
  		this.QuarterIdMap = {};
  		for(let q  of this.Quarters){
  			this.QuarterIdMap[q.id] = q;
  		}
  	}

}
