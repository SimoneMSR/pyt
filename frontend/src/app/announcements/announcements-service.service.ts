import { Injectable } from '@angular/core';
import { Announcement} from './announcement/announcement.model';
import { BaseService} from '../core/base.service';

@Injectable()
export class AnnouncementsService extends  BaseService{

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
