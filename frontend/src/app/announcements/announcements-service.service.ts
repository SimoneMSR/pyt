import { Injectable } from '@angular/core';
import { Announcement} from './announcement/announcement.model';
import { BaseService} from '../core/base.service';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Tag} from './announcement/tag.model';


@Injectable()
export class AnnouncementsService extends  BaseService{

  public Announcements : Announcement[];
  public Tags : Tag[];

  constructor(private http : Http) { 
  	super();
  	this.url='announcement';
  }

  public  getAll(quarterId): Observable<Announcement[]>{
    return this.http
      .get(`${this.baseUrl}/`+this.url+"?quarterId="+quarterId, {headers: this.getHeaders()})
      .map(res => <Announcement[]>res.json());
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
