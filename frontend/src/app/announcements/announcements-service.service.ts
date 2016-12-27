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
    this.setupObservables();

  }


  public  getAll(quarterId): Observable<Announcement[]>{
    return this.http
      .get(this.baseUrl +"/"+this.url+"?quarterId="+quarterId + AnnouncementsService.extractParams((this.params)), {headers: this.getHeaders()})
      .map(res => <Announcement[]>res.json());
  }

  public getAllForUser() : Observable<Announcement[]>{
  	return this.announcementByUser;
  }

  public getAllTags() : Observable<Tag[]> {
  	return this.http
      .get(`${this.baseUrl}/`+this.url+"/tags", {headers: this.getHeaders()})
      .map(res => <Tag[]>res.json());
  }

  private createOrUpdatePrivate(form: Announcement){
  	return this.http
  		.put(`${this.baseUrl}/`+this.url,form, {headers: this.getHeaders()})
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
          this.refreshAnnouncementsByCurrentQuarter(quarter);
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

  private refreshAnnouncementsByCurrentQuarter(quarter : Quarter){
        this.getAll(quarter.id).subscribe(announcements =>
            this.announcementsByCurrentQuarter.next(announcements));
  }

  private static extractParams(params) : String{
    let thisParams= "";
    if(params.filterBy)
      thisParams = thisParams + "&filterBy=" + params.filterBy;
    if(params.top)
      thisParams = thisParams + "&top=" + params.top;
    if(params.skip)
      thisParams = thisParams + "&skip=" + params.skip;
    
    return thisParams;
    }
  }

