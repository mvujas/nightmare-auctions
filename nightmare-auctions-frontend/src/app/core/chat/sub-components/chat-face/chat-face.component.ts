import { Component, OnInit, Input, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-chat-face',
  templateUrl: './chat-face.component.html',
  styleUrls: ['./chat-face.component.scss']
})
export class ChatFaceComponent implements OnInit {

  @Input()
  private username: string;
  @Input()
  private isLastChild: boolean;
  @Input()
  private active: boolean;

  @Output()
  private chatChoosen = new EventEmitter<string>();

  constructor() { }

  ngOnInit() {
    if(this.username == null) {
      throw new Error(
        "Username in ChatFaceComponent cannot be null");
    }
    this.isLastChild = this.isLastChild || false;
    this.active = this.active || false;
  }

  handleClick() {
    this.chatChoosen.emit(this.username);
  }

}
