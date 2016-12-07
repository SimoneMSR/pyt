import { Component, OnInit } from '@angular/core';
import { AuthService} from './core/auth/auth.service';
import { Router }          from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

	public loggedIn : boolean;
	public mapVisible : boolean;
	constructor(private auth: AuthService, private router:Router) { 
		this.mapVisible = false;
		this.loggedIn = false;
	}

	ngOnInit(){
		this.loggedIn = this.auth.isLoggedIn();
		if(!this.loggedIn)
			this.router.navigate(['login']);
	}

	toggleMap(){
  	this.mapVisible = !this.mapVisible;
  }
}
