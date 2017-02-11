import { NgModule } from '@angular/core';
import { LoginComponent } from './login.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { LoginService} from './login.service';
import { AuthModule } from '../core/auth/auth.module';
import { RouterModule, Routes } from '@angular/router'; 
import { CoreModule} from '../core/core.module';
import { FormsModule } from '@angular/forms';
import { ActivateComponent } from './activate/activate.component';

const appRoutes: Routes = [
{ path: 'login', component: LoginComponent },
{ path: 'activate', component : ActivateComponent}
];

@NgModule({
  imports: [
    AuthModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  exports : [
  	LoginComponent
  ],
  providers : [
  	LoginService
  ],
  declarations: [ LoginComponent, LoginFormComponent, ActivateComponent]
})
export class LoginModule { }
