import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { LoginFormComponent } from './login-form/login-form.component';

@NgModule({
  imports: [
    CommonModule
  ],
  exports : [
  	LoginComponent
  ],
  declarations: [LoginComponent, LoginFormComponent]
})
export class LoginModule { }
