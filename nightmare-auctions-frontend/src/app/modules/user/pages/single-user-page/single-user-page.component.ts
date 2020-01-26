import { Component, OnInit } from '@angular/core';
import { User } from '@app/shared/model/user';
import { UserService } from '@app/core/http/user/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ChatService } from '@app/core/services/chat.service';
import { AuthenticationService } from '@app/core/authentication/authentication.service';
import { UserAuthHolder } from '@app/shared/domain/user-auth-holder';

@Component({
  selector: 'app-single-user-page',
  templateUrl: './single-user-page.component.html',
  styleUrls: ['./single-user-page.component.scss']
})
export class SingleUserPageComponent implements OnInit {

  constructor(private userService: UserService,
    private chatService: ChatService,
    private authService: AuthenticationService,
    private route: ActivatedRoute,
    private router: Router) { }

  private userLoadingFailure: boolean = false;
  private user: User = null;
  private userDetails: UserAuthHolder = null;

  ngOnInit() {
    this.route.params.subscribe(this.handlePathParams.bind(this));

    this.authService.basicUserDetails.subscribe(
      newValue => this.userDetails = newValue
    );
  }

  handlePathParams(params) {
    let username: string = params.username;

    this.userService.getByUsername(username).subscribe(
      this.successfulLoadOfData.bind(this),
      this.failedToLoadUser.bind(this)
    );
  }

  failedToLoadUser(response) {
    if(response.status === 404) {
      this.router.navigate(['/404']);
    }
    else {
      this.userLoadingFailure = true;
    }
  }

  successfulLoadOfData(user) {
    this.user = user;
  }

  openChatWithUser(username: string) {
    this.chatService.openChat(username);
  }

}
