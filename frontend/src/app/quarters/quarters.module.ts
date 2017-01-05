import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { QuartersComponent } from './quarters.component';
import { QuarterService} from './';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [QuartersComponent]
})
export class QuartersModule { }
