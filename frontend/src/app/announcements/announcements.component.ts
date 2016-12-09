import { Component, OnInit, Input } from '@angular/core';
import { AnnouncementsService} from './announcements-service.service';
import { Announcement} from './announcement/announcement.model';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css'],
  providers : [AnnouncementsService]
})
export class AnnouncementsComponent implements OnInit {
  public _quarterId=1;
  public announcements : Announcement[];
  constructor(private announcementsService : AnnouncementsService) { 
  	this.announcementsService.getAll(1).subscribe( list => this.announcements = list);
  }

  ngOnInit() {
  }

  @Input()
  set quarterId(quarterid : string){
    switch (quarterid) {
      case "Ingegneria":
      this._quarterId = 1;
        break;
      case "Design":
      this._quarterId=2;
        break;
      case "Informatica":
      this._quarterId=3;
        break;
      case "Economia":
      this._quarterId=4;
        break;
          
      
      default:
      this._quarterId=1;
        break;
    }
    this.announcementsService.getAll(this._quarterId).subscribe(list => this.announcements = list);
  }

}
