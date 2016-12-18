import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapComponent } from './map.component';
import { InlineSVGModule } from 'ng2-inline-svg';
import { AnnouncementsModule} from '../announcements/announcements.module';
import {CoreModule} from '../core/core.module';


@NgModule({
  imports: [
  CoreModule,
    CommonModule,
    InlineSVGModule,
    AnnouncementsModule
  ],
  exports : [
  	MapComponent
  ],
  declarations: [MapComponent]
})
export class MapModule { }
