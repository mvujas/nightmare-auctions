import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ChatService } from '../services/chat.service';
import { User } from '@app/shared/model/user';
import { MessageHolder } from './message-holder';
import { AuthenticationService } from '../authentication/authentication.service';
import { PrivateMessageService } from '../http/private-message/private-message.service';
import { PrivateMessage } from '@app/shared/model/private-message';
declare var $: any;

class CustomUser implements User {
  id: number;
  username: string;
  email: string;
  roles: import("../../shared/model/role").Role[];
  items: import("../../shared/model/item").Item[];
}

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit, AfterViewInit {

  constructor(private chatService: ChatService, 
    private privateMessageService: PrivateMessageService,
    private authService: AuthenticationService) { }

  private users: User[];
  private activeUsername: string;
  private myUsername: string;
  private messages: PrivateMessage[];

  ngOnInit() {

    this.authService.basicUserDetails.subscribe(
      this.myUsernameNewValue.bind(this)
    );
  }

  myUsernameNewValue(value) {
    this.myUsername = (value == null) ? null : value.username

    if(this.myUsername) {
      this.privateMessageService.getAllChaters(this.myUsername).subscribe(
        this.successfullyLoadedChaters.bind(this),
        this.failedToLoadChaters.bind(this)
      );
    }
  }

  successfullyLoadedChaters(users) {
    this.users = users;
  }

  failedToLoadChaters(response) {
    console.log(response);
  }
  
  ngAfterViewInit(): void { 
    let chatControlCommander = $("#chat-control-section");
    let chat = $("#chat-container");

    chatControlCommander.on("click", function() {
      chat.toggleClass("active");
    });
  }

  handleChatChoosen(username: string) {
    this.activeUsername = username;

    this.privateMessageService
      .getMessagesBetweenUsers(this.myUsername, this.activeUsername)
      .subscribe(
        this.successfullyLoadedMessage.bind(this),
        this.failedToLoadMessages.bind(this)
      )
  }

  handleMessageSendSubmission(message: MessageHolder) {
    console.log(message);
  }

  successfullyLoadedMessage(messages) {
    this.messages = messages;
    console.log(messages);
  }

  failedToLoadMessages(response) {
    console.log(response);
  }

}
