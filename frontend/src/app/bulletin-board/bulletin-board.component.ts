import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-bulletin-board',
  templateUrl: './bulletin-board.component.html',
  styleUrls: ['./bulletin-board.component.css']
})
export class BulletinBoardComponent implements OnInit {

	public mapVisible : boolean;
  constructor() { 
  	this.mapVisible = false;
  }

  ngOnInit() {
  }

  toggleMap(){
  	this.mapVisible = !this.mapVisible;
  }
}
