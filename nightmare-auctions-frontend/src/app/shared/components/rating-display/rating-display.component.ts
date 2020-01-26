import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-rating-display',
  templateUrl: './rating-display.component.html',
  styleUrls: ['./rating-display.component.scss']
})
export class RatingDisplayComponent implements OnInit {

  @Input()
  rating: number;

  constructor() { }

  ngOnInit() {
    this.rating = this.rating || 0;
  }

}
