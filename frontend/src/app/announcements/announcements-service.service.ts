import { Injectable } from '@angular/core';
import { Announcement} from './announcement/announcement.model';
import { BaseService, UserService, User} from '../core';
import {QuarterService, Quarter} from '../quarters';
import { Http, Response } from '@angular/http';
import { Observable, BehaviorSubject } from 'rxjs/Rx';
import { Tag} from './announcement/tag.model';


@Injectable()
export class AnnouncementsService extends  BaseService{

  public Announcements : Announcement[];
  public Tags : Tag[];
  public serviceInitialized : BehaviorSubject<boolean>;
  public announcementByUser : BehaviorSubject<Announcement[]>;
  public announcementsByCurrentQuarter : BehaviorSubject<Announcement[]>;
  public params;

  constructor(private http : Http,
    private userService : UserService,
    private quarterService: QuarterService) {
  	super();
    this.params={};
  	this.url='announcement';
  	this.announcementByUser = new BehaviorSubject([]);
    this.announcementsByCurrentQuarter = new BehaviorSubject([]);
    this.serviceInitialized = new BehaviorSubject<boolean>(false);
    this.initialize();
    this.setupObservables();

  }

  public initialize(){
    if(this.Tags == null || this.Tags.length==0){
          this.getAllTags().subscribe(ts => {
            this.Tags=ts;
            this.serviceInitialized.next(true);
    });
    }
  }

  public getSingle(announcementId) : Observable<Announcement>{
    return this.http
      .get(this.baseUrl +"/"+this.url+ "/single" + "?announcementId="+announcementId)
      .map(res => <Announcement>res.json());
  }


  public  getAll(quarterId : number) : Observable<Announcement[]>;
  public  getAll(quarterId : number, params?): Observable<Announcement[]>{

    return this.http
      .get(this.baseUrl +"/"+this.url+"?quarterId="+quarterId +
        AnnouncementsService.extractParams(params)
        , {headers: this.getHeaders()})
      .map(res => <Announcement[]>res.json());
  }

  public getAllForQuarter() : Observable<Announcement[]>{
  	return this.announcementsByCurrentQuarter;
  }

  public getAllTags() : Observable<Tag[]> {
  	return this.http
      .get(`${this.baseUrl}/`+this.url+"/tags", {headers: this.getHeaders()})
      .map(res => <Tag[]>res.json());
  }

  private createOrUpdatePrivate(form: Announcement){
  	return this.http
  		.put(`${this.baseUrl}/`+this.url,form, {headers: this.getHeaders()})
      .finally( () =>{
          this.refreshAnnouncementsByCurrentQuarter(this.quarterService.currentQuarter);
      });
  }

  public createOrUpdate(form: Announcement, quarterId : number){
    return this.createOrUpdatePrivate(form)
      .do( () => {
        if(quarterId == this.userService._user.quarterId){
             this.userService.user.next(this.userService._user);
        }
        if(quarterId == this.quarterService.currentQuarter.id){
              this.quarterService.currentQuarterObservable.next(this.quarterService.currentQuarter);
        }
      });
  }

  private setupObservables(){
    //annunciQ -> currentQuarter
      this.quarterService.currentQuarterObservable.subscribe(quarter => {
          this.refreshAnnouncementsByCurrentQuarter();
      });
      
    //annunciU -> currentUser
    this.userService.user.subscribe(user => {
      this.refreshAnnouncementsByUser(user);
    });
  }

  private refreshAnnouncementsByUser(user : User){
          this.getAll(user.quarterId).subscribe(announcements =>{
            this.announcementByUser.next(announcements);
          });
  }

  public refreshAnnouncementsByCurrentQuarter(){
        this.getAll(this.quarterService.currentQuarter.id,this.params).subscribe(announcements =>
            this.announcementsByCurrentQuarter.next(announcements));
  }

  private static extractParams(params) : String{
    let thisParams= "";
    if(params!=null){
      if(params.filterBy)
        thisParams = thisParams + "&filterBy=" + params.filterBy;
      if(params.orderBy)
        thisParams = thisParams + "&orderBy=" + params.orderBy;
      if(params.top)
        thisParams = thisParams + "&top=" + params.top;
      if(params.skip)
        thisParams = thisParams + "&skip=" + params.skip;
    }
    
    return thisParams;
    }
  }

