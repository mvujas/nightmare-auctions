import { Component, OnInit } from '@angular/core';
import { User } from '@app/shared/model/user';
import { UserService } from '@app/core/http/user/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ChatService } from '@app/core/services/chat.service';
import { AuthenticationService } from '@app/core/authentication/authentication.service';
import { UserAuthHolder } from '@app/shared/domain/user-auth-holder';
import { Item } from '@app/shared/model/item';
import { ItemService } from '@app/core/http/item/item.service';
import { Page } from '@app/shared/domain/page';

@Component({
  selector: 'app-single-user-page',
  templateUrl: './single-user-page.component.html',
  styleUrls: ['./single-user-page.component.scss']
})
export class SingleUserPageComponent implements OnInit {

  constructor(private userService: UserService,
    private authService: AuthenticationService,
    private chatService: ChatService,
    private itemService: ItemService,
    private route: ActivatedRoute,
    private router: Router) { }

  private userLoadingFailure: boolean = false;
  private user: User = null;
  private items: Page<Item> = null;
  private userDetails: UserAuthHolder = null;

  ngOnInit() {
    this.route.params.subscribe(this.handlePathParams.bind(this));
  }

  handlePathParams(params) {
    let username: string = params.username;

    this.userService.getByUsername(username).subscribe(
      this.successfulLoadOfData.bind(this),
      this.failedToLoadUser.bind(this)
    );

    this.authService.basicUserDetails.subscribe(
      value => this.userDetails = value
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

    this.itemService
      .getAllFiltered(`username=${this.user.username}`).subscribe(
        items => this.items = items
      );
  }

  openChatWithUser(username: string) {
    this.chatService.openChat(username);
  }

}
