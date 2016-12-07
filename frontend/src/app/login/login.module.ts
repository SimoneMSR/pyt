import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { LoginService} from './login.service';
import { FormsModule } from '@angular/forms';
import { AuthModule } from '../core/auth/auth.module';
import { RouterModule, Routes } from '@angular/router'; 

const appRoutes: Routes = [
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AuthModule,
    RouterModule.forRoot(appRoutes)
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
