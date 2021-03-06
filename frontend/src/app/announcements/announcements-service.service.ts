import { Injectable } from '@angular/core';
import { Announcement} from './announcement/announcement.model';
import { BaseService} from '../core';
import { LoginService} from "../login";
import {Member} from "../member";
import {Quarter} from '../quarters/';
import { Http, Response } from '@angular/http';
import { Observable, BehaviorSubject } from 'rxjs/Rx';
import { Tag} from './announcement/tag.model';
import { QuarterService} from '../quarters';


@Injectable()
export class AnnouncementsService extends  BaseService{

  public Announcements : Announcement[];
  public Tags : Tag[];
  public serviceInitialized : BehaviorSubject<boolean>;
  public announcementByUser : BehaviorSubject<Announcement[]>;
  public announcementsByCurrentQuarter : BehaviorSubject<Announcement[]>;
  public params;

  constructor(private http : Http,
    private loginService : LoginService,
    private quarterService: QuarterService) {
  	super();
    this.params={};
  	this.url=this.baseUrl + '/announcement';
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
      .get(this.url+ "/single" + "?announcementId="+announcementId)
      .map(res => <Announcement>res.json());
  }

  public  getAll(quarterId : number, params?): Observable<Announcement[]>{

    return this.http
      .get(this.url+"?quarterId="+quarterId +
        AnnouncementsService.extractParams(params)
        , {headers: this.getHeaders()})
      .map(res => <Announcement[]>res.json());
  }

  public getAllForQuarter() : Observable<Announcement[]>{
  	return this.announcementsByCurrentQuarter;
  }

  public getAllTags() : Observable<Tag[]> {
  	return this.http
      .get(this.url+"/tags", {headers: this.getHeaders()})
      .map(res => <Tag[]>res.json());
  }

  private createOrUpdatePrivate(form: Announcement){
  	return this.http
  		.put(this.url,form, {headers: this.getHeaders()})
      ._do( () =>{
          this.refreshAnnouncementsByCurrentQuarter();
      });
  }

  public createOrUpdate(form: Announcement, quarterId : number){
    return this.createOrUpdatePrivate(form)
      .do( () => {
        if(quarterId == this.loginService.user.quarterId){
             this.loginService.userObservable.next(this.loginService.user);
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
    this.loginService.userObservable.subscribe(user => {
      if(user != null)
        this.refreshAnnouncementsByUser(user);
    });
  }

  private refreshAnnouncementsByUser(user : Member){
          this.getAll(user.quarterId).subscribe(announcements =>{
            this.announcementByUser.next(announcements);
          });
  }

  public refreshAnnouncementsByCurrentQuarter(){
        this.getAll(this.quarterService.currentQuarter.id,this.params)
            .subscribe(announcements => {
            this.announcementsByCurrentQuarter.next(announcements);
    });
  }

  public getByCreatorId(creatorId : number,params?) : Observable<Announcement[]>{
    return this.http
      .get(this.url+ "/createdBy" +"?creatorId="+creatorId +
        AnnouncementsService.extractParams(params)
        , {headers: this.getHeaders()})
      .map(res => <Announcement[]>res.json());
  }


  }

