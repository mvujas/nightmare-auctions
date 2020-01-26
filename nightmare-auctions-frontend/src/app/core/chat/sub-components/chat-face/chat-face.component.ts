import { Component, OnInit, Input } from '@angular/core';

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

  constructor() { }

  ngOnInit() {
    if(this.username == null) {
      throw new Error(
        "Username in ChatFaceComponent cannot be null");
    }
    this.isLastChild = this.isLastChild || false;
  }

}
