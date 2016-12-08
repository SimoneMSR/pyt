import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BulletinBoardComponent } from './bulletin-board.component';
import { RouterModule, Routes } from '@angular/router'; 
import { AnnouncementsModule} from '../announcements/announcements.module';

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
  declarations: [BulletinBoardComponent]
})
export class BulletinBoardModule { }
