import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication/authentication.service';
declare var $: any;

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent implements OnInit {

  constructor(private router: Router, private authService: AuthenticationService) { }

  private isUserAuthenticated: boolean;

  ngOnInit() {
    this.authService.isAuthenticated.subscribe(newValue => this.isUserAuthenticated = newValue);

    this.checkNavBarColor();
    $(document).ready(() => $(document).scroll(this.checkNavBarColor));
  }

  checkNavBarColor() {
    let $nav = $('#main-navigation-bar');
    $nav.toggleClass('scrolled', $(document).scrollTop() > $nav.height());
  }

  logout() {
    this.authService.logout();
  }

}
