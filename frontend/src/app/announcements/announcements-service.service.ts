import { Injectable } from '@angular/core';
import { Announcement} from './announcement/announcement.model';
import { BaseService, UserService} from '../core';
import { Http, Response } from '@angular/http';
import { Observable, BehaviorSubject } from 'rxjs/Rx';
import { Tag} from './announcement/tag.model';


@Injectable()
export class AnnouncementsService extends  BaseService{

  public Announcements : Announcement[];
  public Tags : Tag[];
  public announcementByUser : BehaviorSubject<Announcement[]>;

  constructor(private http : Http,private userSevice : UserService) { 
  	super();
  	this.url='announcement';
  	this.announcementByUser = new BehaviorSubject([]);
  	this.userSevice.user.subscribe( user => 
      this.getAll(user.quarterId).subscribe(announcements => 
        this.announcementByUser.next(announcements)));
  }


  public  getAll(quarterId): Observable<Announcement[]>{
    return this.http
      .get(this.baseUrl +"/"+this.url+"?quarterId="+quarterId, {headers: this.getHeaders()})
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

  public createOrUpdate(form: Announcement){
  	return this.http
  		.put(`${this.baseUrl}/`+this.url,form, {headers: this.getHeaders()});
  }


}
