import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MemberComponent, MemberService } from './';

@NgModule({
  imports: [
    CommonModule
  ],
  providers : [MemberService],
  declarations: [MemberComponent]
})
export class MemberModule { }
