import { Component, OnInit } from '@angular/core';
import { GreetingService } from '@app/core/http/greeting/greeting.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  constructor(private greetingService: GreetingService) { }

  ngOnInit() {
    this.greetingService.login("admin", "admin123").subscribe(console.log);

    this.greetingService.greeting().subscribe(console.log)
  }

}
