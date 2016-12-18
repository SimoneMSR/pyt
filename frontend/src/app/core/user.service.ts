import { Injectable } from '@angular/core';
import { Subject,BehaviorSubject } from 'rxjs/Rx';
import {User} from "./";

@Injectable()
export class UserService {
	public user : Subject<User>;
	public _user : User;
  constructor() {
    this._user = new User();
    this._user.quarterId=1;
  	this.user = new BehaviorSubject<User>(this._user);
  }

  public updateUserQuarterId(id : number){
  	this._user.quarterId=id;
  	this.user.next(this._user);
  }

}
