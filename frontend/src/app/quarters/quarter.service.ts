import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import {Observable, Subject, BehaviorSubject} from 'rxjs/Rx';
import {BaseService} from '../core/base.service';
import {LoginService} from '../login/login.service';
import {Quarter} from './quarter.model';

@Injectable()
export class QuarterService  extends BaseService{

	public Quarters : Quarter[];
	public QuarterIdMap : {[key:string]:Quarter};
  public currentQuarterObservable : Subject<Quarter>;
  public currentQuarter : Quarter;
  public serviceInitialized : BehaviorSubject<Boolean>;
  constructor(private http: Http,
              private loginService: LoginService) { 
  	super();
  	this.url = "quarter";
    this.currentQuarter = null;
    this.currentQuarterObservable = new Subject<Quarter>();
    this.serviceInitialized = new BehaviorSubject(false);

    this.setupObservables();
  	}


   public getAll() : Observable<Quarter[]> {
  		return this.http
  		      .get(this.baseUrl+"/"+this.url, {headers: this.getHeaders()})
     		  .map(res => <Quarter[]>res.json());

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

    private loadQuarterIdMap() : Promise<void>{
     return this.getAll()
        .map( res => {
          this.Quarters = res;
          this.buildQuarterIdMap();
          this.serviceInitialized.next(true);
         }).toPromise();
    }

    private setupObservables(){
      this.loadQuarterIdMap().then(() => {
         this.loginService.userObservable.subscribe(user => {
                  if(user!=null){
                    this.setCurrentQuarter(this.QuarterIdMap[user.quarterId]);
           }
         });        
      });
    }

}
