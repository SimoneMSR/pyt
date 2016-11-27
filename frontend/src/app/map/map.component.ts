import { Component, 
	  OnInit,
	  Input,
	  trigger,
	  state,
	  style,
	  transition,
	  animate } from '@angular/core';


@Component({
  selector: 'app-map',
  template: `
  	<div [inlineSVG]="'/app/map/map.svg'" [@collapse]="mapState"></div>
  `,
  styleUrls: ['./map.component.css'],
  animations : [
  	trigger('collapse', [
			state('visible', style({height: '*'})),
			state('collapsed', style({height: '0'})),
			transition('visible <=> collapsed', animate('500ms'))
  		])
  ]
})
export class MapComponent implements OnInit {

  mapState : string;
  viewBox : string;
  defaultViewBox :  string;
  defaultCollapsedViewBox : string;

  constructor() { 
  	this.viewBox = this.defaultCollapsedViewBox = '0 0 749.09998 0';
  	this.defaultViewBox = '0 0 749.09998 617.09998';
  }

  ngOnInit() {
  }

  @Input()
  set visible(visible : boolean){
  	this.mapState = visible ? 'visible' : 'collapsed';
  	this.viewBox = visible ? this.defaultViewBox : this.defaultCollapsedViewBox;
  }
}
