import { Directive, 
	Component, 
	OnInit, 
  AfterViewInit ,
  AfterViewChecked,
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
import { CommentPyt, CommentsService} from "../../comments";

@Component({
  selector: 'app-announcement-modal',
  templateUrl: './announcement-modal.component.html',
  styleUrls: ['./announcement-modal.component.css'],
  exportAs : 'announcementmodal'
})
export class AnnouncementModalComponent implements OnInit {
	public announcement  : Announcement;
	public cathegories : String[];
	public tags : String[];
  public quarters : String[];
	public availableTags : string[];
  public availableQuarters : string[];
  public comments : CommentPyt[];
  public comment : CommentPyt;
	@ViewChild('newAnnouncementModal') public newAnnouncementModal:ModalDirective;
  	@Input() announcementInput : Announcement;

	@Output() close = new EventEmitter();

    onClickedExit() {
        this.close.emit('event');
    }

  constructor(private injector: Injector,
      private viewContainerRef: ViewContainerRef, 
      private service : AnnouncementsService,
      private quarterService : QuarterService,
      private commentsService : CommentsService) { 
  	this.cathegories = ['IDEA','PROPOSAL' ,'PROBLEM'];
  }

  ngOnInit(){
    this.comment = new CommentPyt();
    this.service.serviceInitialized.subscribe(isInitialized =>{
      if(isInitialized){
          this.quarterService.serviceInitialized.subscribe(qIsInitialized => {
            if(qIsInitialized){
              
              this.announcementInput = this.injector.get('announcementInput');
              this.announcement = this.announcementInput != undefined ? this.announcementInput : new Announcement(null);
              this.refreshComments();
              this.tags = this.announcement.tags ? Announcement.extractTags(this.announcement.tags) :[];
              this.quarters = this.announcement.tags ? this.quarterService.Quarters.filter(q=> this.announcement.quarters.indexOf(q.id)>=0).map(q=>q.name) : [];
              this.availableTags = Announcement.extractTags(this.service.Tags);
              this.availableQuarters = this.quarterService.Quarters.map(q=> q.name.toString());
              new Promise((resolve) => setTimeout(resolve, 100))
                .then(()=>{
                    this.showModal();
                });
            }
          });

      }
    });
  }

  protected refreshComments(){
              if(this.announcement.id){
                  this.commentsService.byAnnouncement(this.announcement.id).subscribe(
                    response => this.comments=response,
                    error => alert(error)
                    );
              }
  }
      

  public showModal():void {
    this.newAnnouncementModal.show();
  }
 
  public hideModal():void {
    this.newAnnouncementModal.hide();
  }

  public createOrUpdate(){
  		this.formatDto();
  		this.service.createOrUpdate(this.announcement, this.quarterService.currentQuarter.id)
  		.subscribe(result => {
          this.hideModal();
      });
  } 

  private formatDto(){
      this.announcement.tags=this.service.Tags.filter( t => this.tags.indexOf(t.name)>=0);
      //this.announcement.cathegory=this.announcement.cathegory.toUpperCase();
      this.announcement.quarters = this.quarterService.Quarters.filter(q=> this.quarters.indexOf(q.name)>=0).map(q=>q.id);
      this.announcement = new Announcement(this.announcement);
      this.announcement.quarters = this.quarterService.Quarters.filter(q=> this.quarters.indexOf(q.name)>=0).map(q=>q.id);
  }


  public postComment(){
      this.commentsService.postComment(this.announcement.id, this.comment).subscribe(resut => {
        this.refreshComments();
      } ,error => {
        alert(error);
      });
  }

}
