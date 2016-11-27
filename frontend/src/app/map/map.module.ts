import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapComponent } from './map.component';
import { InlineSVGModule } from 'ng2-inline-svg';

@NgModule({
  imports: [
    CommonModule,
    InlineSVGModule
  ],
  exports : [
  	MapComponent
  ],
  declarations: [MapComponent]
})
export class MapModule { }
