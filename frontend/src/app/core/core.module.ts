import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreComponent } from './core.component';
import { BaseService} from './base.service';
import { HttpModule } from '@angular/http';


@NgModule({
  imports: [
    CommonModule, HttpModule
  ],
  providers : [
  	BaseService
  ], 
  declarations: [CoreComponent]
})
export class CoreModule { }
