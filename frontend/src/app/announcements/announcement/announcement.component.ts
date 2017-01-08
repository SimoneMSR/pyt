import { Component, OnInit , Input} from '@angular/core';
import {FavouriteService} from "../";

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {
  public isFavourited : boolean;
  constructor(private favouriteService : FavouriteService) { }

  ngOnInit() {
  	for( let tag of this.announcement.tags){
	  	tag.color= this.hashStringToColor(tag.name);
  	}
  	switch(this.announcement.cathegory){
  		case 'IDEA' : {this.announcement.color = '#66FF66'; break}
  		case 'PROBLEM' : {this.announcement.color = '#66B2FF'; break}
  		case 'PROPOSAL' : {this.announcement.color = '#FF9933'; break}
      default : {this.announcement.color = '#C0C0C0';}
  	}
    this.favouriteService.favouritesObservable.subscribe(favs => {
      this.isFavourited = this.favouriteService.favourites.indexOf(this.announcement.id)>=0;
    });
  }

private djb2(str){
  var hash = 5381;
  for (var i = 0; i < str.length; i++) {
    hash = ((hash << 5) + hash) + str.charCodeAt(i); /* hash * 33 + c */
  }
  return hash;
}

private hashStringToColor(str) {
  var hash = this.djb2(str);
  var r = (hash & 0xFF0000) >> 16;
  var g = (hash & 0x00FF00) >> 8;
  var b = hash & 0x0000FF;
  return "#" + ("0" + r.toString(16)).substr(-2) + ("0" + g.toString(16)).substr(-2) + ("0" + b.toString(16)).substr(-2);
}

click(){
  console.log("ciao");
}

  @Input() announcement;

}
