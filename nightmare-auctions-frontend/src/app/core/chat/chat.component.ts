import { Component, OnInit, AfterViewInit } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngOnInit() {
    
  }
  
  ngAfterViewInit(): void {
    let chatControlCommander = $("#chat-control-section");
    let chat = $("#chat-container")

    chatControlCommander.on("click", function() {
      chat.toggleClass("active");
    });
  }

}
