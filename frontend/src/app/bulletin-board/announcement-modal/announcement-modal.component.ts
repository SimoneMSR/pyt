import { Directive, 
	Component, 
	OnInit, 
	OnDestroy, 
	ViewChild, 
	ViewContainerRef, 
	Input, 
	Output, 
	EventEmitter,
	Injector} from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/components/modal/modal.component';
import { Announcement} from '../../announcements/announcement/announcement.model';
import { Tag} from '../../announcements/announcement/tag.model';
import { AnnouncementsService} from '../../announcements/announcements-service.service';
import { QuarterService} from "../../quarters";

@Component({
  selector: 'app-announcement-modal',
  templateUrl: './announcement-modal.component.html',
  styleUrls: ['./announcement-modal.component.css'],
  exportAs : 'announcementmodal'
})
export class AnnouncementModalComponent implements OnInit , OnDestroy{
	public announcement  : Announcement;
	public cathegories : String[];
	public tags : String[];
  public quarters : String[];
	public availableTags : string[];
  public availableQuarters : string[];
	@ViewChild('newAnnouncementModal') public newAnnouncementModal:ModalDirective;
  	@Input() announcementInput : Announcement;

	@Output() close = new EventEmitter();

    onClickedExit() {
        this.close.emit('event');
    }

  constructor(private injector: Injector,private viewContainerRef: ViewContainerRef, 
    private service : AnnouncementsService,
    private quarterService : QuarterService) { 
  	this.cathegories = ['Idea','Proposal' ,'Problem'];
  }

  ngOnInit() {
  	this.announcementInput = this.injector.get('announcementInput');
  	this.announcement = this.announcementInput != undefined ? this.announcementInput : new Announcement();
  	this.tags = this.announcement.tags ? Announcement.extractTags(this.announcement.tags) :[];
  	this.availableTags = [];
    this.availableQuarters = this.quarterService.Quarters.map(q=> q.name.toString());
    this.service.getAllTags().subscribe(ts => {
      this.service.Tags=ts;
      this.availableTags = Announcement.extractTags(ts);
      this.newAnnouncementModal.show();
    });

  }

  ngOnDestroy(){
  	console.log("viva gianni");
  }

  public showModal():void {
    this.newAnnouncementModal.show();
  }
 
  public hideModal():void {
    this.newAnnouncementModal.hide();
  }

  public createOrUpdate(){
  		this.announcement.tags= [];
  		for(let t of this.tags)
  			this.announcement.tags.push(this.service.Tags[0]);
  		this.announcement.cathegory=this.announcement.cathegory.toUpperCase();
  		this.service.createOrUpdate(this.announcement)
  			.subscribe(result => { console.log(result);
          this.hideModal();
      });
  } 

}
