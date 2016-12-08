import { Injectable } from '@angular/core';
import { Announcement} from './announcement/announcement.model';

@Injectable()
export class AnnouncementsService {

  public Announcements : Announcement[];

  constructor() { 
  	this.Announcements = [ new Announcement('rinasco'),
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

}
