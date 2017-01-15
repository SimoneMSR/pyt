import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { RouterModule, Routes } from '@angular/router'; 
import {LoginGuard} from "../core/login.guard";
import { AnnouncementsModule} from '../announcements/announcements.module';
import { MessagesComponent,MessageComponent, MessagesService  } from './';

const appRoutes: Routes = [
{ path: 'home', component: HomeComponent , canActivate : [LoginGuard] }
];

@NgModule({
  imports: [
    CommonModule,
    AnnouncementsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers : [MessagesService],
  declarations: [HomeComponent, MessagesComponent, MessageComponent]
})
export class HomeModule { }
