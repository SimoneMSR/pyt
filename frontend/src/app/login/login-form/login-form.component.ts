import { Component, OnInit } from '@angular/core';
import { Login} from './login.model';
import { LoginService} from '../login.service';
import { AuthService} from '../../core/auth/auth.service';

 


@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

	public credential : Login;
	public submitted : boolean;

  constructor(private auth:AuthService){
  	this.credential = new Login('simone','password');
  	this.submitted = false;
	}
  	onSubmit() { this.submitted = true;}

  ngOnInit() {
  }

}
