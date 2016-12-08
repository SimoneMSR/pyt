import { Component, OnInit } from '@angular/core';
import { Announcement} from '../announcements/announcement/announcement.model';
import { AnnouncementsService} from '../announcements/announcements-service.service';


@Component({
  selector: 'app-bulletin-board',
  templateUrl: './bulletin-board.component.html',
  styleUrls: ['./bulletin-board.component.css'],
  providers : [AnnouncementsService]
})
export class BulletinBoardComponent implements OnInit {


  public announcements : Announcement[];
  constructor(private announcementsService : AnnouncementsService) { 
    this.announcements = this.announcementsService.Announcements;
  }

  ngOnInit() {
  }


}
