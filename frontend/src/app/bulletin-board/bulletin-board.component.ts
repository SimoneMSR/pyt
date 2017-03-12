import { Component, OnInit, ViewContainerRef, ViewChild } from '@angular/core';
import { Announcement, AnnouncementsService, AnnouncementQueryParam } from '../announcements';
import { AnnouncementModalComponent, AnnouncementModalDirective} from './';
import {Being, Department} from "../core";


@Component({
  selector: 'app-bulletin-board',
  templateUrl: './bulletin-board.component.html',
  styleUrls: ['./bulletin-board.component.css'],
  providers : [AnnouncementsService],
  entryComponents : [AnnouncementModalComponent]
})
export class BulletinBoardComponent implements OnInit {
  public modalData : any;
  private viewContainerRef: ViewContainerRef;
  public queryParams : AnnouncementQueryParam;
  public announcements : Announcement[];
  public searchInput : string;
  public creatorBeingsFilter :any[];
  public targetBeingsFilter :any[];
  public departmentFilter : any[];
  public titleFilter : boolean;
  public Being;
  public Department;
  constructor(private announcementsService : AnnouncementsService,
    viewContainerRef:ViewContainerRef) { 
    this.viewContainerRef = viewContainerRef;
  }

  ngOnInit() {
    this.Being = Being;
    this.Department = Department;
    this.searchInput="";
    this.creatorBeingsFilter = [{name: Being.STARTUP},{name:Being.STUDENT},{name:Being.COMPANY}, {name:Being.PA},{name: Being.OTHER}];
    this.targetBeingsFilter = [{name: Being.STARTUP},{name:Being.STUDENT},{name:Being.COMPANY}, {name:Being.PA},{name: Being.OTHER}];
    this.departmentFilter = [{name: Department.DESIGN}, {name: Department.ECONOMICS}, {name: Department.ENGENEERING}, {name: Department.OTHER}];
    this.titleFilter=false;
    this.queryParams = new AnnouncementQueryParam();
    this.announcements=[];
    this.refreshAnnouncements();
  }

  public applyParams(){
    this.queryParams.createdBy = this.creatorBeingsFilter.filter( e => e.selected).map(e=> e.name);
    this.queryParams.targets = this.targetBeingsFilter.filter( e => e.selected).map(e=> e.name);
    this.queryParams.departmentTargets = this.departmentFilter.filter( e => e.selected).map(e=> e.name);
    this.refreshAnnouncements();
  }

  private refreshAnnouncements(){
    this.announcementsService.getAll(this.queryParams).subscribe( list => {
      this.announcements = list;
    });
  }

  private listenToServicesEvents(){
    this.announcementsService.announcementChanged.subscribe(() =>{
      this.refreshAnnouncements();
    });
  }



  select(ann:Announcement){
    this.announcementsService.getSingle(ann.id).subscribe(announcement => {
      announcement.likes = ann.likes;
      announcement.dislikes = ann.dislikes;
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


  toggleSearch(show){
    if(!show){
      delete this.queryParams.title;
      this.searchInput="";
      this.refreshAnnouncements();
    }else{
      this.search(undefined);
    }
  }

  orderBy(criterium){
    this.queryParams.orderBy = criterium;
    this.refreshAnnouncements();
  }

  search(string){
    if(string)
      this.queryParams.title=string;
    this.refreshAnnouncements();
  }
}
