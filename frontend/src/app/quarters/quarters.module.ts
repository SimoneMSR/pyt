import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuartersComponent } from './quarters.component';
import { QuarterService} from './';

@NgModule({
  imports: [
    CommonModule
  ],
  providers : [QuarterService],
  declarations: [QuartersComponent]
})
export class QuartersModule { }
