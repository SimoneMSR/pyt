import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BulletinBoardComponent } from './bulletin-board.component';
import { RouterModule, Routes } from '@angular/router'; 
import { MapModule} from '../map/map.module';
import { MapComponent} from '../map/map.component';

const appRoutes: Routes = [
  { path: 'board', component: BulletinBoardComponent }
];

@NgModule({
  imports: [
    CommonModule, 
    MapModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [BulletinBoardComponent]
})
export class BulletinBoardModule { }
