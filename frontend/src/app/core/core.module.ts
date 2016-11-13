import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreComponent } from './core.component';
import { BaseService} from './base.service';

@NgModule({
  imports: [
    CommonModule
  ],
  exports : [
  	BaseService
  ],
  providers : [
  	BaseService
  ], 
  declarations: [BaseService, CoreComponent]
})
export class CoreModule { }
