import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { RouterModule, Routes } from '@angular/router'; 
import {LoginGuard} from "../core/login.guard";

const appRoutes: Routes = [
{ path: 'home', component: HomeComponent , canActivate : [LoginGuard] }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [HomeComponent]
})
export class HomeModule { }
