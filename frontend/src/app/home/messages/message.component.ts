import { Component, OnInit, Input } from '@angular/core';
import {Message} from "../";
import {MemberService, Member} from "../../member";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

	@Input() message : Message;
  constructor(private memberService : MemberService) { 
  }

  ngOnInit() {
  	this.message.creator = new Member("","");
  	this.memberService.getSingle(this.message.creatorId).subscribe(member => {
  		this.message.creator = member;
  	});
  }


}
