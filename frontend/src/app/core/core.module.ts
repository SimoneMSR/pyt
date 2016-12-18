import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreComponent } from './core.component';
import { BaseService} from './base.service';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';


@NgModule({
  imports: [
    CommonModule, HttpModule, FormsModule, BrowserModule
  ],
  providers : [
  	BaseService
  ], 
  declarations: [CoreComponent]
})
export class CoreModule { }
