import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { LoginFormComponent } from './login-form/login-form.component';
import {LoginService} from './login.service';
import {Member} from './member.model'

@NgModule({
  imports: [
    CommonModule
  ],
  exports : [
  	LoginComponent,
  	Member
  ],
  providers : [
  	LoginService
  ],
  declarations: [ Member, LoginComponent, LoginFormComponent]
})
export class LoginModule { }
