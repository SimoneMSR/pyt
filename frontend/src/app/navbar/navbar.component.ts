import { Component, OnInit } from '@angular/core';
import {QuarterService} from "../quarters";
import {LoginService} from "../login";
import { Router }          from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

	public quarterName : String;
	public userName : String;
  constructor(private quarterService : QuarterService,
  		private loginService : LoginService,
  		private router : Router) {
  	this.userName="";
  	this.quarterService.currentQuarterObservable.subscribe(q=>{
  		this.quarterName = q.name;
  	});
  	this.loginService.userObservable.subscribe(u=> {
  		if(u!=null){
  			this.userName=u.name;
  		}
  	});
  }

  ngOnInit() {
  }

  logout(){
  	this.loginService.logout();
  	this.router.navigate(['login']);
  }

}
