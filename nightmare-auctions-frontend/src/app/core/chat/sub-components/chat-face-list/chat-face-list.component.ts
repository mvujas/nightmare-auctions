import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { User } from '@app/shared/model/user';

@Component({
  selector: 'app-chat-face-list',
  templateUrl: './chat-face-list.component.html',
  styleUrls: ['./chat-face-list.component.scss']
})
export class ChatFaceListComponent implements OnInit {

  @Output()
  private chatChoosen = new EventEmitter<string>();

  @Input()
  private activeUsername: string;
  @Input()
  private users: User[];

  constructor() { }

  ngOnInit() {
  }

  chatFaceChanged(username: string) {
    this.chatChoosen.emit(username);
  }

}
