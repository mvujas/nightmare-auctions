import { Component, OnInit } from '@angular/core';
import { NgbDate, NgbCalendar, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { ItemService } from '@app/core/http/item/item.service';
import { AuthenticationService } from '@app/core/authentication/authentication.service';

@Component({
  selector: 'app-sold-items-statistics',
  templateUrl: './sold-items-statistics.component.html',
  styleUrls: ['./sold-items-statistics.component.scss']
})
export class SoldItemsStatisticsComponent implements OnInit {

  constructor(private itemService: ItemService,
    private authService: AuthenticationService) {}

  ngOnInit(): void {
    this.authService.basicUserDetails.subscribe(
      val => this.basicUserDetails = val
    );
  }

  private basicUserDetails = null;
  private dataLoading = {
    triedLoading: false,
    error: false,
    items: null,
    from: null,
    to: null
  };

  incDateByADay(date: Date): Date {
    let result: Date = new Date(date);
    result.setDate(result.getDate() + 1);
    return result;
  }

  handleSubmitCallback(value) {
    if(this.basicUserDetails != null) {
      let username = this.basicUserDetails.username;
      let from = this.incDateByADay(value.from);
      let to = this.incDateByADay(value.to);
      this.dataLoading.triedLoading = true;
      this.itemService
        .getSoldItemsStatisticsInPeriod(username, from, to)
        .subscribe(
          val => this.successfulStatisticsLoading(val, value.from, value.to),
          this.failedStatisticsLoading.bind(this)
        )
    }
    else {
      console.log('Cannot get username');
    }
  }

  failedStatisticsLoading(response) {
    console.log(response)
    this.dataLoading = {
      triedLoading: true,
      error: true,
      items: null,
      from: null,
      to: null
    };
  }

  successfulStatisticsLoading(soldItems, from, to) {
    this.dataLoading = {
      triedLoading: true,
      error: false,
      items: soldItems,
      from: from,
      to: to
    };
  }

}
