import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Observable, BehaviorSubject} from 'rxjs/Rx';
import {BaseService, UserService} from '../core';
import {Quarter} from './';

@Injectable()
export class QuarterService  extends BaseService{

	public Quarters : Quarter[];
	public QuarterIdMap : {[key:string]:Quarter};
  public currentQuarterObservable : BehaviorSubject<Quarter>;
  public currentQuarter : Quarter;
  public serviceInitialized : BehaviorSubject<Boolean>;
  constructor(private http: Http,
              private userService:UserService) { 
  	super();
  	this.url = "quarter";
    this.currentQuarter = new Quarter();
    this.currentQuarter.id=1;
    this.currentQuarterObservable = new BehaviorSubject<Quarter>(this.currentQuarter);
    this.serviceInitialized = new BehaviorSubject(false);
    this.setupObservables();
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

    public setCurrentQuarter(q : Quarter){
      this.currentQuarter = q;
      this.currentQuarterObservable.next(q);
    }

    private setupObservables(){
      this.getAll()
        .subscribe( res => {
          this.Quarters = res;
          this.buildQuarterIdMap();
          this.serviceInitialized.next(true);
          //currentQuarter -> currentUser
          this.userService.user.subscribe(user => {
            this.currentQuarterObservable.next(this.QuarterIdMap[user.quarterId]);
          });
      });
        
    }

}
