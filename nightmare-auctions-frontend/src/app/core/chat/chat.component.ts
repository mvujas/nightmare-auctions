import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { ChatService } from '../services/chat.service';
import { User } from '@app/shared/model/user';
import { MessageHolder } from './message-holder';
import { AuthenticationService } from '../authentication/authentication.service';
import { PrivateMessageService } from '../http/private-message/private-message.service';
import { PrivateMessage } from '@app/shared/model/private-message';
import { ChatContentComponent } from './sub-components/chat-content/chat-content.component';
declare var $: any;

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  @ViewChild('chatContent', { static: false })
  private chatContent: ChatContentComponent;

  constructor(private chatService: ChatService, 
    private privateMessageService: PrivateMessageService,
    private authService: AuthenticationService) { }

  private users: User[];
  private emptyUsers: User[] = [];
  private activeUsername: string;
  private allActiveUserChats: User[] = null;
  private myUsername: string;
  private messages: PrivateMessage[];
  private lastMessageDate: Date = null;
  
  private activeChatersInterval = null;
  private messagesInterval = null;

  ngOnInit() {
    this.chatService.waitForRequest().subscribe(
      this.newChatUserRequested.bind(this)
    );

    this.authService.basicUserDetails.subscribe(
      this.myUsernameNewValue.bind(this)
    );
  }

  myUsernameNewValue(value) {
    this.myUsername = (value == null) ? null : value.username

    if(this.myUsername) {
      this.loadChatters();
    }
    else {
      this.activeUsername = null;
      this.messages = null;
      this.users = null;
    }
  }

  handleChatChoosen(username: string) {
    if(this.activeUsername != username) {
      this.activeUsername = username;

      this.clearMessagesInterval();
      this.lastMessageDate = null;
      this.loadMessages();
    }
  }
  
  handleMessageSendSubmission(message: MessageHolder) {
    this.sendMessage(message);
  }

  // =============| START GETTERS AND LCASS CHANGERS START |=============
  isActive() {
    return $("#chat-container").hasClass("active");
  }

  openChat() {
    $("#chat-container").addClass("active");
  }

  toggleActive() {
    $("#chat-container").toggleClass("active");
  }
  // =============| START GETTERS AND LCASS CHANGERS END |=============

  // =============| EMPTY USERS TOOLS START |=============
  newChatUserRequested(username) {
    if(this.myUsername != username) {
      let isAlreadyIn = false;

      for(let i = 0; i < this.emptyUsers.length && !isAlreadyIn; i++) {
        isAlreadyIn = isAlreadyIn || this.emptyUsers[i].username == username;
      }

      if(!isAlreadyIn) {
        this.emptyUsers.unshift({
          id: null,
          username: username,
          email: null,
          roles: null,
          items: null
        });
      }

      this.updateAllActiveChaters();
    }
    this.openChat();
    this.handleChatChoosen(username);
    
  }

  cleanEmptyUsers() {
    this.emptyUsers = this.emptyUsers.filter((val => {
      if(this.users == null) {
        return true;
      }
      let isIn = false;
      for(let i = 0; i < this.users.length && !isIn; i++) {
        isIn = isIn || (val.username == this.users[i].username);
      }
      return !isIn;
    }).bind(this));
  }

  updateAllActiveChaters() {
    this.cleanEmptyUsers();
    if(this.users != null) {
      this.allActiveUserChats = this.emptyUsers.concat(this.users);
    }
    else {
      this.allActiveUserChats = this.emptyUsers;
    }
  }
  // =============| EMPTY USERS TOOLS END |=============

  // =============| INTERVALS START |=============
  setActiveChatersInterval() {
    if(this.activeChatersInterval == null) {
      this.activeChatersInterval = setInterval(
        this.loadChatters.bind(this), 3000
      );
    }
  }

  clearActiveChatersInterval() {
    if(this.activeChatersInterval != null) {
      clearInterval(this.activeChatersInterval);
      this.activeChatersInterval = null;
    }
  }

  setMessagesInterval() {
    if(this.messagesInterval == null) {
      this.messagesInterval = setInterval(
        this.loadMessages.bind(this), 3000
      );
    }
  }

  clearMessagesInterval() {
    if(this.messagesInterval != null) {
      clearInterval(this.messagesInterval);
      this.messagesInterval = null;
    }
  }
  // =============| INTERVALS END |=============

  // =============| LOADING CHATTERS START |=============
  loadChatters() {
    if(this.myUsername == null) {
      this.clearActiveChatersInterval();
    }
    if(!this.isActive() && this.users != null) {
      return;
    }

    this.privateMessageService.getAllChaters(this.myUsername).subscribe(
      this.successfullyLoadedChaters.bind(this),
      this.failedToLoadChaters.bind(this)
    );
  }

  successfullyLoadedChaters(users) {
    this.users = users;

    this.updateAllActiveChaters();

    this.setActiveChatersInterval();
  }

  failedToLoadChaters(response) {
    console.log(response);
  }
  // =============| LOADING CHATTERS END |=============

  // =============| LOADING MESSAGES START |=============
  loadMessages() {
    if(this.myUsername == null) {
      this.clearMessagesInterval();
    }
    if(!this.isActive() && this.messages != null) {
      return;
    } 

    this.privateMessageService
      .getMessagesBetweenUsersSince(this.myUsername, this.activeUsername, this.lastMessageDate)
      .subscribe(
        this.successfullyLoadedMessage.bind(this),
        this.failedToLoadMessages.bind(this)
      );
  }

  successfullyLoadedMessage(messages: PrivateMessage[]) {
    if(this.lastMessageDate == null) {
      this.messages = messages;
    }
    else {
      this.messages = this.messages.concat(messages);
    }
    
    setTimeout(
      () => this.chatContent.scrollMessagesToBottom(), 
      50);

    if(messages.length > 0) {
      let lastMessage: PrivateMessage = messages[messages.length - 1];
      let lastDate: Date = new Date(lastMessage.sendingTime);
      this.lastMessageDate = new Date(lastDate.getTime() + 1000);
    }

    this.setMessagesInterval();
  }

  failedToLoadMessages(response) {
    console.log(response);
  }
  // =============| LOADING MESSAGES END |=============

  // =============| SENDING MESSAGE START |=============
  sendMessage(message: MessageHolder) {
    this.privateMessageService.sendMessage(message.username, message.text).subscribe(
      this.successfullySentMessage.bind(this),
      this.failedToSendMessage.bind(this)
    );
  }


  successfullySentMessage(_val) {
    this.clearMessagesInterval();
    this.loadMessages();
  }

  failedToSendMessage(response) {
    console.log(response);
  }
  // =============| SENDING MESSAGE END |=============

}
