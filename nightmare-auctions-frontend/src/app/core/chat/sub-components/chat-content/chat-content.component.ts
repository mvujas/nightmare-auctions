import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-chat-content',
  templateUrl: './chat-content.component.html',
  styleUrls: ['./chat-content.component.scss']
})
export class ChatContentComponent implements OnInit {

  constructor() { }

  private message: string = '';

  ngOnInit() {
  }

  tryToSendMessage() {
    console.log(this.message);
    this.message = "";
  }

}
