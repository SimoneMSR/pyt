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
import {AnnouncementQueryParam} from "./";


@Injectable()
export class AnnouncementsService extends  BaseService{

  public Announcements : Announcement[];
  public Tags : Tag[];
  public serviceInitialized : BehaviorSubject<boolean>;
  public announcementChanged : BehaviorSubject<boolean>;
  public announcementByUser : BehaviorSubject<Announcement[]>;
  public params;

  constructor(private http : Http,
    private loginService : LoginService,
    private quarterService: QuarterService) {
  	super();
    this.params={};
  	this.url=this.baseUrl + '/announcement';
  	this.announcementByUser = new BehaviorSubject([]);
    this.serviceInitialized = new BehaviorSubject<boolean>(false);
    this.announcementChanged = new BehaviorSubject<boolean>(false);
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

  public  getAll(params?): Observable<Announcement[]>{
    var _params : AnnouncementQueryParam;
    if(params !=null)
      _params = <AnnouncementQueryParam>params;
    return this.http
      .post(this.url+"/get",
        _params
        , {headers: this.getHeaders()})
      .map(res => <Announcement[]>res.json());
  }

  public getAllTags() : Observable<Tag[]> {
  	return this.http
      .get(this.url+"/tags", {headers: this.getHeaders()})
      .map(res => <Tag[]>res.json());
  }

  private createOrUpdatePrivate(form: Announcement) : Observable<Response>{
  	return this.http
  		.put(this.url,form, {headers: this.getHeaders()})
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
        this.announcementChanged.next(true);
      });
  }

  private setupObservables(){  
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

  public getByCreatorId(creatorId : number,params?) : Observable<Announcement[]>{
    var _params : AnnouncementQueryParam;
    if(params !=null)
      _params = <AnnouncementQueryParam>params;
    return this.http
      .post(this.url+ "/createdBy" +"?creatorId="+creatorId,
        _params
        , {headers: this.getHeaders()})
      .map(res => <Announcement[]>res.json());
  }


  }

