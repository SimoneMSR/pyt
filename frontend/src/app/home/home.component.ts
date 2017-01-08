import { Component, OnInit } from '@angular/core';
import { AnnouncementsService, Announcement, FavouriteService} from "../announcements";
import { LoginService} from "../login";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
	public myAnnouncements : Announcement[];
	public myFavourites : Announcement[];
  constructor(private announcementsService : AnnouncementsService,
  	private loginService : LoginService,
  	private favouriteService : FavouriteService) { 
  	this.myAnnouncements=[];
  	this.myFavourites=[];
  }

  ngOnInit() {
  	this.announcementsService.getByCreatorId(this.loginService.user.id).subscribe(result => {
  		this.myAnnouncements=result;
  	});
  	this.favouriteService.getFavourites().subscribe (result => {
  		this.myFavourites = result;
  	});

  }

}
