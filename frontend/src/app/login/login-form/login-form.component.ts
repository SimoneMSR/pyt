import { Component, OnInit } from '@angular/core';
import { Login} from './login.model';
import { LoginService} from '../login.service';
import { AuthService} from '../../core/auth/auth.service';
import { Router }          from '@angular/router';

 


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

	public credential : Login;
	public submitted : boolean;

  constructor(private auth:AuthService, private router: Router){
  	this.credential = new Login('simone','password');
  	this.submitted = false;
	}
  	onSubmit() { this.submitted = true;}

  ngOnInit() {
  }

  public login(username, password){
    var l = this.auth.login(username,password);
  }

}
