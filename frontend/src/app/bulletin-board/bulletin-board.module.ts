import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BulletinBoardComponent } from './bulletin-board.component';
import { RouterModule, Routes } from '@angular/router'; 
import { AnnouncementsModule} from '../announcements/announcements.module';
import { AnnouncementModalComponent } from './announcement-modal/announcement-modal.component';
import { ModalDirective } from 'ng2-bootstrap/components/modal/modal.component';
import { ComponentsHelper, ModalBackdropComponent} from 'ng2-bootstrap';

const appRoutes: Routes = [
  { path: '', component: BulletinBoardComponent },
  { path: 'board', component: BulletinBoardComponent }
];

@NgModule({
  imports: [
    CommonModule, 
    RouterModule.forRoot(appRoutes),
    AnnouncementsModule
  ],
  providers : [ComponentsHelper, ModalBackdropComponent],
  declarations: [BulletinBoardComponent, AnnouncementModalComponent, ModalDirective]
})
export class BulletinBoardModule { }
