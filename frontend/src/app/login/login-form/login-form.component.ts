import { Component, OnInit } from '@angular/core';
import { Login, LoginService} from '../';
import {Member} from "../../member";
import { AuthService} from '../../core/auth/auth.service';
import { Router }          from '@angular/router';
import { MemberService} from "../../member";

 


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

	public credential : Login;
	public submitted : boolean;
  public message : string;
  constructor(private auth:AuthService, 
      private router: Router,
      private service : LoginService,
      private memberService : MemberService){
  	this.credential = new Login("","");
  	this.submitted = false;
    this.message="";
	}
  	onSubmit() { this.submitted = true;}

  ngOnInit() {
  }

  public login(){
    //var l = this.auth.login(this.credential.email,this.credential.password);
    this.message="";
    this.service.login(this.credential).subscribe(result => {
      this.service.setUserLoggedIn(<Member>result.json());
      this.router.navigate(['board']);
    }, error=> {
      this.message = "Wrong username/email";
    });
  }

  public register(){
    this.message="";
    this.memberService.register(this.credential).subscribe(result => {
      this.message="Please verify this registration by clicking the activation link we just sent you to your mail";
    }, error => {
      alert("An error occured. Please contact sviluppo.pyt@gmail.com for support.");
    });
  }

}
