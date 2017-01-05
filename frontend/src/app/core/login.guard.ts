import {CanActivate} from "@angular/router";
import {Injectable} from "@angular/core";
import {LoginService} from "../login/login.service";
import { Router} from '@angular/router'; 
 
@Injectable()
export class LoginGuard implements CanActivate{
	constructor(private loginService : LoginService,
		private router : Router){
	}
	
	canActivate () : boolean{
		let authorized = this.loginService.user!=null;
		if(!authorized)
			this.router.navigate(['login']);
    	return authorized;
  }
}