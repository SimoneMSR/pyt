import { Component, OnInit, ViewContainerRef, ViewChild } from '@angular/core';
import { Announcement, AnnouncementsService} from '../announcements';
import { AnnouncementModalComponent, AnnouncementModalDirective} from './';
import {UserService} from "../core";


@Component({
  selector: 'app-bulletin-board',
  templateUrl: './bulletin-board.component.html',
  styleUrls: ['./bulletin-board.component.css'],
  providers : [AnnouncementsService],
  entryComponents : [AnnouncementModalComponent]
})
export class BulletinBoardComponent implements OnInit {
  public grid : boolean = true; // variabile per vista lista / grid 
  public modalData : any;
  private viewContainerRef: ViewContainerRef;
  public filterCathegory : any;
  public announcements : Announcement[];
  constructor(private announcementsService : AnnouncementsService,  viewContainerRef:ViewContainerRef) { 
    
    this.viewContainerRef = viewContainerRef;
    this.filterCathegory = { Idea : true, Proposal : true, Problem : true};
    this.announcementsService.getAllForQuarter().subscribe( list => {
      this.announcements = list;
    });
    //this.announcements = this.announcementsService.Announcements;
  }

  ngOnInit() {
  }

  select(ann:Announcement){
    this.announcementsService.getSingle(ann.id).subscribe(announcement => {
      this.modalData = {
        component : AnnouncementModalComponent,
        inputs : {
          announcementInput : announcement
        }
      };
    });
  }

  create(){
     this.modalData = {
      component : AnnouncementModalComponent,
      inputs : {
        announcementInput : undefined
      }
    };
  }

  applyFilter(){
    let filter="";
    if(this.filterCathegory.Idea)
      filter=filter+"IDEA";
    if(this.filterCathegory.Proposal)
      filter=filter+"PROPOSAL";
    if(this.filterCathegory.Problem)
      filter=filter+"PROBLEM";
    this.announcementsService.params.filterBy = filter;
    this.announcementsService.refreshAnnouncementsByCurrentQuarter();
  }

  orderBy(criterium){
    this.announcementsService.params.orderBy = criterium;
    this.announcementsService.refreshAnnouncementsByCurrentQuarter();
  }
}
