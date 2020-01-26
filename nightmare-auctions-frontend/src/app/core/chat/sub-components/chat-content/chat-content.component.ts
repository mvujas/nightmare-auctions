import { Component, OnInit, AfterViewInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MessageHolder } from '../../message-holder';
import { PrivateMessage } from '@app/shared/model/private-message';


@Component({
  selector: 'app-chat-content',
  templateUrl: './chat-content.component.html',
  styleUrls: ['./chat-content.component.scss']
})
export class ChatContentComponent implements OnInit, AfterViewInit {

  @Input()
  private activeUsername: string;
  @Input()
  private myUsername: string;
  @Input()
  private messages: PrivateMessage[];
  @Output()
  private sendMessage = new EventEmitter<MessageHolder>();

  ngAfterViewInit(): void {
    this.scrollMessagesToBottom();
  }

  constructor() { }

  private message: string = '';

  ngOnInit() {
    if(this.activeUsername == null) {
      throw new Error(
        "Active username cannot be null in ChatContentComponent");
    }
    if(this.myUsername == null) {
      throw new Error(
        "My username cannot be null in ChatContentComponent");
    }
  }
  
  gotoBottom(id){
    let element = document.getElementById(id);
    element.scrollTop = element.scrollHeight - element.clientHeight;
  }

  scrollMessagesToBottom() {
    this.gotoBottom("message-list");
  }

  tryToSendMessage() {
    this.sendMessage.emit({
      username: this.activeUsername,
      text: this.message.trim()
    });
    this.message = "";
  }

}
