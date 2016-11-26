import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreComponent } from './core.component';
import { BaseService} from './base.service';

@NgModule({
  imports: [
    CommonModule
  ],
  providers : [
  	BaseService
  ], 
  declarations: [CoreComponent]
})
export class CoreModule { }
