import { Component, 
  OnInit, 
  Input, 
  Output,
  EventEmitter} from '@angular/core';
import { AnnouncementsService} from './';
import { Announcement} from './announcement/announcement.model';

@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.css'],
  providers : [AnnouncementsService]
})
export class AnnouncementsComponent implements OnInit {
    public grid : boolean = true; // variabile per vista lista / grid
    public announcements : Announcement[];
    public searchString : String; 
  @Output() onSelected = new EventEmitter<Announcement>();
  @Output() onSearch = new EventEmitter<String>();
  constructor() { 
  	
  }

  ngOnInit() {
  }

  select(selected : Announcement){
    this.onSelected.emit(selected);
  }

  search(){
    this.onSearch.emit(this.searchString);
  }

  @Input()
  set searchInput(input : String){
    this.searchString = input;
  }

  @Input()
  set announcementsList(list : Announcement[]){
    this.announcements = list;
  }


}
