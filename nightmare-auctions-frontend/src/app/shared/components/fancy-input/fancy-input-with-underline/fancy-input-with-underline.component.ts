import { Component, OnInit } from '@angular/core';
import { FancyInputComponent } from '../fancy-input.component';

@Component({
  selector: 'app-fancy-input-with-underline',
  templateUrl: '../fancy-input.component.html',
  styleUrls: [
    '../fancy-input.component.scss', 
    './fancy-input-with-underline.component.scss'
  ]
})
export class FancyInputWithUnderlineComponent extends FancyInputComponent {

  constructor() {
    super();
  }

}