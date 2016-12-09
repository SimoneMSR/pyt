import { Component, OnInit } from '@angular/core';
import { AnnouncementsService} from './announcements-service.service';
import { Announcement} from './announcement/announcement.model';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css'],
  providers : [AnnouncementsService]
})
export class AnnouncementsComponent implements OnInit {

  public announcements : Announcement[];
  constructor(private announcementsService : AnnouncementsService) { 
  	this.announcementsService.getAll(1).subscribe( list => this.announcements = list);
  }

  ngOnInit() {
  }

}
