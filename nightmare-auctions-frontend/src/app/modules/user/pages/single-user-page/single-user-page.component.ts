import { Component, OnInit } from '@angular/core';
import { User } from '@app/shared/model/user';
import { UserService } from '@app/core/http/user/user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-single-user-page',
  templateUrl: './single-user-page.component.html',
  styleUrls: ['./single-user-page.component.scss']
})
export class SingleUserPageComponent implements OnInit {

  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private router: Router) { }

  private userLoadingFailure: boolean = false;
  private user: User = null;

  ngOnInit() {
    this.route.params.subscribe(this.handlePathParams.bind(this));
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

}
