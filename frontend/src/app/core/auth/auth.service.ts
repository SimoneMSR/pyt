import { Injectable }      from '@angular/core';
import { tokenNotExpired } from 'angular2-jwt';
import { Router }          from '@angular/router';

// Avoid name not found warnings
declare var Auth0Lock: any;
declare var Auth0: any; 

@Injectable()
export class AuthService {
  // Configure Auth0
  //lock = new Auth0Lock('wCQj2e7peoLyE6I5LxKgMMdQZbOqch8C', 'simonemsr.eu.auth0.com', {});

  // auth0 = new Auth0({
	 //  domain: 'simonemsr.eu.auth0.com',
	 //  clientID: 'wCQj2e7peoLyE6I5LxKgMMdQZbOqch8C',
	 //  responseType: 'token',
	 //  //callbackURL: 'https://localhost:8080/pyt/rest/login',
  // });

  private loggedIn : boolean;

  constructor(private router: Router) {
    // this.loggedIn = true;
    // var result = this.auth0.parseHash(window.location.hash);

    // if (result && result.idToken) {
    //   localStorage.setItem('id_token', result.idToken);
    //   this.router.navigate(['board']);
    // } else if (result && result.error) {
    //   alert('error: ' + result.error);
    // }
  }

  public isLoggedIn(){
    return this.loggedIn;
  }
 
 public login(email, password) {
  // this.loggedIn = true;
  // return this.auth0.login({
  //   connection: 'Username-Password-Authentication',
  //   responseType: 'token',
  //   email: email,
  //   password: password,
  // }, function(err) {
  //   if (err) alert("something went wrong: " + err.message);
  // });
};

  public authenticated() {
    // Check if there's an unexpired JWT
    // This searches for an item in localStorage with key == 'id_token'
    return tokenNotExpired();
  };

  public logout() {
    // Remove token from localStorage
    localStorage.removeItem('id_token');
  };

  public signUp(email, password) {
	  // this.auth0.signup({
	  //   connection: 'Username-Password-Authentication',
	  //   responseType: 'token',
	  //   email: email,
	  //   password: password,
	  // }, function(err) {
	  //   if (err) alert("something went wrong: " + err.message);
	  // });
  };

  public googleLogin() {
	 //  this.auth0.login({
	 //    connection: 'google-oauth2'
	 //  }, function(err) {
	 //    if (err) alert("something went wrong: " + err.message);
	 //  });
  }
}