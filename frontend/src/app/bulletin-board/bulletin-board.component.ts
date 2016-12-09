import { Component, OnInit, ViewContainerRef, ViewChild  } from '@angular/core';
import { Announcement} from '../announcements/announcement/announcement.model';
import { AnnouncementsService} from '../announcements/announcements-service.service';
import { AnnouncementModalComponent} from './announcement-modal/announcement-modal.component';

@Component({
  selector: 'app-bulletin-board',
  templateUrl: './bulletin-board.component.html',
  styleUrls: ['./bulletin-board.component.css'],
  providers : [AnnouncementsService]
})
export class BulletinBoardComponent implements OnInit {
  @ViewChild('childModal') public childModal:AnnouncementModalComponent;


  private viewContainerRef: ViewContainerRef;
  public announcements : Announcement[];
  constructor(private announcementsService : AnnouncementsService,  viewContainerRef:ViewContainerRef) { 
    this.announcementsService.getAll(2).subscribe( list => this.announcements = list);
    this.viewContainerRef = viewContainerRef;
    //this.announcements = this.announcementsService.Announcements;
  }

  ngOnInit() {
  }


}
