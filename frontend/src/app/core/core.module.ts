import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreComponent } from './core.component';
import { BaseService} from './base.service';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { QuartersModule} from '../quarters/quarters.module';


@NgModule({
  imports: [
    CommonModule, 
    HttpModule, 
    FormsModule, 
    BrowserModule, 
    QuartersModule
  ],
  providers : [
  	BaseService
  ], 
  declarations: [CoreComponent]
})
export class CoreModule { }
