import { Component, 
	  OnInit,
	  Input,
    Output,
	  trigger,
	  state,
	  style,
	  transition,
	  animate,
    EventEmitter } from '@angular/core';


@Component({
  selector: 'app-map',
  templateUrl: 'map.component.html',
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
  _visible : boolean;
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>()


  constructor() { 
    this.viewBox = this.defaultCollapsedViewBox = '0 0 749.09998 0';
    this.defaultViewBox = '0 0 749.09998 617.09998';
    this._visible=false;
  }

    toggleMap(){
      this._visible = !this._visible;
      this.visibleChange.emit(this._visible);
  }

  ngOnInit() {
  }

  @Input()
  set visible(visible : boolean){
    this.mapState = visible ? 'visible' : 'collapsed';
    this.viewBox = visible ? this.defaultViewBox : this.defaultCollapsedViewBox;
    this._visible = visible;
    this.visibleChange.emit(visible);
  }

  get visible() : boolean{
    return this._visible;
  }

}
