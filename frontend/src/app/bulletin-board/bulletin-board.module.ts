import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BulletinBoardComponent } from './bulletin-board.component';
import { RouterModule, Routes } from '@angular/router'; 
import { AnnouncementComponent } from './announcement/announcement.component';

const appRoutes: Routes = [
  { path: '', component: BulletinBoardComponent },
  { path: 'board', component: BulletinBoardComponent }
];

@NgModule({
  imports: [
    CommonModule, 
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [BulletinBoardComponent, AnnouncementComponent]
})
export class BulletinBoardModule { }
