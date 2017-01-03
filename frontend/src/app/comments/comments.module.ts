import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommentsComponent } from './comments.component';
import {CommentsService, CommentPyt} from "./";

@NgModule({
  imports: [
    CommonModule
  ],
  providers : [
  	CommentsService
  ],
  declarations: [CommentsComponent]
})
export class CommentsModule { }
