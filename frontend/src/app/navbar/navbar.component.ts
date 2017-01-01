import { Component, OnInit } from '@angular/core';
import {QuarterService} from "../quarters";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

	public quarterName : String;
  constructor(private quarterService : QuarterService) {
  	this.quarterService.currentQuarterObservable.subscribe(q=>{
  		this.quarterName = q.name;
  	});
  }

  ngOnInit() {
  }

}
