import { Component, OnInit , Input} from '@angular/core';

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.css']
})
export class AnnouncementComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  	for( let tag of this.announcement.tags){
	  	if(tag.title < 1/5)
	  		tag.color = "#337ab7";
	  	else 
	  		if(tag.title < 2/5)
	  			tag.color = "#5cb85c";
	  		else 
	  			if(tag.title < 3/5)
	  				tag.color = "#5bc0de";
	  			else
	  				if(tag.title < 4/5)
	  					tag.color = "#f0ad4e";
	  				else
	  					tag.color = "#d9534f";
  	}

  }

  @Input() announcement;

}
