import { Injectable } from '@angular/core';
import { Announcement} from './announcement/announcement.model';
import { BaseService} from '../core/base.service';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { Tag} from './announcement/tag.model.ts';


@Injectable()
export class AnnouncementsService extends  BaseService{

  public Announcements : Announcement[];
  public Tags : Tag[];

  constructor(private http : Http) { 
  	super();
  	this.url='announcement';
  	this.getAllTags().subscribe(tags => this.Tags = tags);
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


}
