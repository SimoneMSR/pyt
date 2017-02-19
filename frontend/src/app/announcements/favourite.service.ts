import {BaseService} from "../core";
import { Http, Response } from '@angular/http';
import {Observable, Subject} from "rxjs/Rx";
import { Injectable } from '@angular/core';
import { LoginService} from "../login";
import {Announcement, AnnouncementsService} from "../announcements";

@Injectable()
export class FavouriteService extends BaseService{
	public favourites :  number[];
	public favouritesObservable : Subject<number[]>;
	constructor(private http : Http,
			private loginService : LoginService) { 
	  	super();
	  	this.url = this.baseUrl + "/favourite";
	  	this.favourites = [];
	  	this.favouritesObservable = new Subject<number[]>();
	  	this.setupObservables();
  }


  public getFavouritesIds() : Observable<number[]>{
  	return this.http
  			.get(this.url + "/ids", {headers : this.getHeaders()})
  			.map(res => <number[]>res.json());
  }


  public getFavourites(params?) : Observable<Announcement[]>{
  	return this.http
  			.get(this.url , {headers : this.getHeaders()})
  			.map(res => <Announcement[]>res.json());
  }

  public postFavourite(announcementId : number) : Observable<Response>{
  	return this.http
  			.post(this.url + "?announcementId=" + announcementId,{},
  				{headers : this.getHeaders()});

  }

  private setupObservables(){
  		this.loginService.userObservable.subscribe( user => {
  			this.refreshFavourites();
  		});
  }

  public refreshFavourites(){
  	this.getFavouritesIds().subscribe(list => {
  		this.favourites = list;
  		this.favouritesObservable.next(list);
  	});  	
  }
}