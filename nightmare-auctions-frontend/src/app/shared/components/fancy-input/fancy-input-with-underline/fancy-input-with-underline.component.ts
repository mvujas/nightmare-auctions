import { Component, OnInit, forwardRef } from '@angular/core';
import { FancyInputComponent } from '../fancy-input.component';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-fancy-input-with-underline',
  templateUrl: '../fancy-input.component.html',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FancyInputWithUnderlineComponent),
      multi: true
    }
  ],
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