import { Directive, Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { ModalDirective } from 'ng2-bootstrap/components/modal/modal.component';

@Component({
  selector: 'app-announcement-modal',
  templateUrl: './announcement-modal.component.html',
  styleUrls: ['./announcement-modal.component.css'],
  exportAs : 'announcementmodal'
})
export class AnnouncementModalComponent implements OnInit {
  @ViewChild('newAnnouncementModal') public newAnnouncementModal:ModalDirective;
  constructor(private viewContainerRef: ViewContainerRef) { }

  ngOnInit() {
  }

  public showModal():void {
    this.newAnnouncementModal.show();
  }
 
  public hideModal():void {
    this.newAnnouncementModal.hide();
  }

}
