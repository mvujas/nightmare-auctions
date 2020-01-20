import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
declare var $: any;

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    this.checkNavBarColor();
    $(document).ready(() => $(document).scroll(this.checkNavBarColor));
  }

  checkNavBarColor() {
    let $nav = $('#main-navigation-bar');
    $nav.toggleClass('scrolled', $(document).scrollTop() > $nav.height());
  }

}
