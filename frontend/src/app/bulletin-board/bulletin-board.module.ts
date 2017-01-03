import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BulletinBoardComponent } from './bulletin-board.component';
import { RouterModule, Routes } from '@angular/router'; 
import { AnnouncementsModule} from '../announcements/announcements.module';
import { AnnouncementModalComponent } from './announcement-modal/announcement-modal.component';
import { ModalDirective, ModalModule } from 'ng2-bootstrap';
import { ComponentsHelper, ModalBackdropComponent} from 'ng2-bootstrap';
import { CoreModule} from '../core/core.module';
import { FormsModule } from '@angular/forms';
import {RlTagInputModule} from 'angular2-tag-input';
import { BrowserModule } from '@angular/platform-browser';
import { TagNamePipe } from './announcement-modal/tagName.pipe';
import {AnnouncementModalDirective} from './announcement-modal/announcement-modal.directive';
import  'rxjs/add/operator/do';
import { TabsModule } from 'ng2-bootstrap';
import { UiSwitchModule } from 'angular2-ui-switch';
import { CommentsModule} from '../comments/comments.module';


const appRoutes: Routes = [
  { path: 'board', component: BulletinBoardComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
    TabsModule,
    AnnouncementsModule,
    ModalModule,
    CommonModule,
    CoreModule,
    FormsModule,
    RlTagInputModule,
    BrowserModule,
    UiSwitchModule,
    CommentsModule
  ],
  providers : [ComponentsHelper, ModalBackdropComponent],
  declarations: [BulletinBoardComponent, AnnouncementModalComponent,AnnouncementModalDirective, TagNamePipe]
})
export class BulletinBoardModule { }
