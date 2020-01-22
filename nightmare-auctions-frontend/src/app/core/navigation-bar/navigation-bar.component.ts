import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication/authentication.service';
import { RedirectUrlService } from '../services/redirect-url.service';
import { UserDetails } from '@app/shared/domain/UserDetails';
declare var $: any;

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent implements OnInit {

  constructor(private router: Router, 
    private authService: AuthenticationService,
    private redirectUrlService: RedirectUrlService) { }

  private userDetails: UserDetails;

  ngOnInit() {
    this.authService.basicUserDetails.subscribe(
      newUserDetails => this.userDetails = newUserDetails);

    this.checkNavBarColor();
    $(document).ready(() => $(document).scroll(this.checkNavBarColor));
  }

  checkNavBarColor() {
    let $nav = $('#main-navigation-bar');
    $nav.toggleClass('scrolled', $(document).scrollTop() > $nav.height());
  }

  logout() {
    this.authService.logout();
    this.redirectUrlService.redirectToUrl(this.router.url); // Enforces authorization
  }

}
