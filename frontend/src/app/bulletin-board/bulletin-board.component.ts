import { Component, OnInit } from '@angular/core';
import { AnnouncementComponent} from './announcement/announcement.component';
import { Announcement} from './announcement/announcement.model';


@Component({
  selector: 'app-bulletin-board',
  templateUrl: './bulletin-board.component.html',
  styleUrls: ['./bulletin-board.component.css']
})
export class BulletinBoardComponent implements OnInit {


  public announcements : Announcement[];
  constructor() { 
    this.announcements = [ new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo')],new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),
      new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),
      new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),
      new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),
      new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo'),
      new Announcement('rinasco'),
      new Announcement('trionfo'),
      new Announcement('risolvo');
  }

  ngOnInit() {
  }


}
