import { Component, OnInit } from '@angular/core';
import { AnnouncementsService, Announcement, FavouriteService} from "../announcements";
import { LoginService} from "../login";
import {Message} from "./";
import {MessagesService} from "./messages/messages.service";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
	public myAnnouncements : Announcement[];
	public myFavourites : Announcement[];
	public messages : Message[];
  constructor(private announcementsService : AnnouncementsService,
  	private loginService : LoginService,
  	private favouriteService : FavouriteService,
  	private messagesService : MessagesService) { 
  	this.myAnnouncements=[];
  	this.myFavourites=[];
  	this.messages=[];
  }

  ngOnInit() {
  	this.announcementsService.getByCreatorId(this.loginService.user.id).subscribe(result => {
  		this.myAnnouncements=result;
  	});
  	this.favouriteService.getFavourites().subscribe (result => {
  		this.myFavourites = result;
  	});
  	this.messagesService.getMessagesOrdered().subscribe(result => {
  		this.messages=result;
  	})
  	this.messagesService.getConversation().subscribe(result => {
  		
  	});

  }

  searchMines(string){
    this.announcementsService.getByCreatorId(this.loginService.user.id,{"title" : string}).subscribe(result => {
  		this.myAnnouncements=result;
  	});
  }

  searchFavourites(string){
  	this.favouriteService.getFavourites({"title" : string}).subscribe (result => {
  		this.myFavourites = result;
  	});
  }

}
