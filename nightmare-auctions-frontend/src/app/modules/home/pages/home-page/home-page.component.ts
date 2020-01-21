import { Component, OnInit } from '@angular/core';
import { GreetingService } from '@app/core/http/greeting/greeting.service';
import { AuthenticationService } from '@app/core/authentication/authentication.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  constructor(private greetingService: GreetingService, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    /*this.authenticationService.login("admin", "admin123").subscribe(
      x => {
        this.greetingService.message().subscribe(console.log)
      }
    );*/
  }

}
