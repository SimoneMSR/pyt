import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../login";
import {Router, ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-activate',
  templateUrl: './activate.component.html',
  styleUrls: ['./activate.component.css']
})
export class ActivateComponent implements OnInit {

  constructor(private loginService : LoginService,
  	private activatedRoute : ActivatedRoute,
  	private router : Router) { }

  ngOnInit() {
  	this.activatedRoute.queryParams.subscribe((params: Params) => {
        var email = params['email'];
        var hash = params['hash'];
  		this.loginService.activate(email,hash).subscribe(response => {
  			this.router.navigate(['login']);
  		}, error => {
  			alert("An error occured during activation of " + email + "!");
  		});
    }
  }

}
