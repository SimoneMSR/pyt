import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { LoginService} from './login.service';
import { FormsModule } from '@angular/forms';
import { AuthModule } from '../core/auth/auth.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AuthModule
  ],
  exports : [
  	LoginComponent
  ],
  providers : [
  	LoginService
  ],
  declarations: [ LoginComponent, LoginFormComponent]
})
export class LoginModule { }
