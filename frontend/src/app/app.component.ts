import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { AuthService} from './core/auth/auth.service';
import {LoginService} from './login';
import { Router }          from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

	public loggedIn : boolean;
	public mapVisible : boolean;
	private viewContainerRef: ViewContainerRef;
	public isLoggedIn :boolean;

	constructor(private auth: AuthService, 
		private router:Router, viewContainerRef:ViewContainerRef,
		private loginService : LoginService) { 
		this.mapVisible = false;
		this.loggedIn = false;
		this.viewContainerRef = viewContainerRef;
		this.loginService.userObservable.subscribe(u=> {
  			this.isLoggedIn= u!=null;
  	});
	}

	ngOnInit(){
		this.loggedIn = this.loginService.user!=null;
		if(!this.loggedIn)
			this.router.navigate(['login']);
		else
			this.router.navigate(['board']);
	}
}
