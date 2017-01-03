import { Component, OnInit } from '@angular/core';
import { Login, Member, LoginService} from '../';
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

  constructor(private auth:AuthService, 
      private router: Router,
      private service : LoginService){
  	this.credential = new Login('simone','password');
  	this.submitted = false;
	}
  	onSubmit() { this.submitted = true;}

  ngOnInit() {
  }

  public login(){
    //var l = this.auth.login(this.credential.email,this.credential.password);
    this.service.login(this.credential).subscribe(result => {
      this.service.setUserLoggedIn(<Member>result.json());
      this.router.navigate(['board']);
      this.service.userObservable.next(this.service.user);
    }, error=> {
      alert("Wrong username/email");
    });
  }

}
