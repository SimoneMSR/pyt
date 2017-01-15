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
import { Tag} from '../../announcements/announcement/tag.model';
import { Announcement, AnnouncementsService, LikeService, FavouriteService} from '../../announcements';
import { QuarterService} from "../../quarters";
import { CommentPyt, CommentsService} from "../../comments";
import {LoginService} from "../../login";
import {Message, MessagesService} from "../../home";

@Component({
  selector: 'app-announcement-modal',
  templateUrl: './announcement-modal.component.html',
  styleUrls: ['./announcement-modal.component.css'],
  exportAs : 'announcementmodal'
})
export class AnnouncementModalComponent implements OnInit {
	public announcement  : Announcement;
	public cathegories : any[];
	public tags : String[];
  public quarters : String[];
	public availableTags : string[];
  public availableQuarters : string[];
  public comments : CommentPyt[];
  public comment : CommentPyt;
  public canModify : boolean;
  public isFavourited : boolean;
  public contact : Message;
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
      private commentsService : CommentsService,
      private likeService : LikeService,
      private loginService : LoginService,
      private favoruiteService : FavouriteService,
      private messageService : MessagesService) { 
    this.cathegories = [{'label' : 'Idea', 'value' : 'IDEA'},
      {'label' : 'Proposal', 'value' : 'PROPOSAL'},
      {'label' : 'Problem', 'value' : 'PROBLEM'}];
      this.contact = new Message();
  }

  ngOnInit(){
    this.comment = new CommentPyt();
    this.service.serviceInitialized.subscribe(isInitialized =>{
      if(isInitialized){
          this.quarterService.serviceInitialized.subscribe(qIsInitialized => {
            if(qIsInitialized){
              
              this.announcementInput = this.injector.get('announcementInput');
              this.canModify = this.announcementInput != undefined ? this.announcementInput.creator.id == this.loginService.user.id : true;
              this.announcement = this.announcementInput != undefined ? this.announcementInput : new Announcement(null);
              if(this.announcementInput != undefined){
                this.isFavourited = this.favoruiteService.favourites.indexOf(this.announcementInput.id)>=0;
                this.contact.object = this.announcementInput.title;
              }else
                this.isFavourited = false;
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
      this.announcement.cathegory=this.announcement.cathegory.toUpperCase();
      this.announcement.quarters = this.quarterService.Quarters.filter(q=> this.quarters.indexOf(q.name)>=0).map(q=>q.id);
      this.announcement = new Announcement(this.announcement);
      this.announcement.quarters = this.quarterService.Quarters.filter(q=> this.quarters.indexOf(q.name)>=0).map(q=>q.id);
  }


  public postComment(){
      this.commentsService.postComment(this.announcement.id, this.comment).subscribe(resut => {
        this.comment.content="";
        this.refreshComments();
      } ,error => {
        alert(error);
      });
  }

  public like(dislike : boolean){
      this.likeService.like(this.announcement.id,dislike)
        .subscribe(
          result => {
              this.likeService.countLike(this.announcement.id).subscribe(announcement => {
                  this.announcement.dislikes=announcement.dislikes;
                  this.announcement.likes=announcement.likes;
              });
              this.service.refreshAnnouncementsByCurrentQuarter(); 
          },
          error => {
            alert(error);
          });

  }

  public archive(){
    this.favoruiteService.postFavourite(this.announcement.id).subscribe( result => {
      this.favoruiteService.refreshFavourites();
      this.isFavourited = !this.isFavourited;
    });
  }

  public sendMessage(){
    this.contact.creatorId=this.loginService.user.id;
    this.contact.date = new Date();
    this.contact.recipients = [this.announcement.creator.id];
    this.messageService.sendMessage(this.contact).subscribe(result => {
      this.close.emit('event');
    });
  }

}
