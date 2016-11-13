import { Component, OnInit } from '@angular/core';
import {Member} from '../member.model';
import {LoginService} from '../login.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

	public member : Member;
	public submitted : boolean;

  constructor(){
  	this.member = new Member('simone','password');
  	this.submitted = false;
	}
  	onSubmit() { this.submitted = true;}

  ngOnInit() {
  }

}
