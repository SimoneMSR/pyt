import { Component, OnInit } from '@angular/core';
import { AnnouncementsService, Announcement} from "../announcements";
import { LoginService} from "../login";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
	public myAnnouncements : Announcement[];
  constructor(private announcementsService : AnnouncementsService,
  	private loginService : LoginService) { 
  	this.myAnnouncements=[];
  }

  ngOnInit() {
  	this.announcementsService.getByCreatorId(this.loginService.user.id).subscribe(result => {
  		this.myAnnouncements=result;
  	});
  }

}
