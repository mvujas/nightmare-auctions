import { Component, OnInit, Input, AfterViewInit } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-fancy-input',
  templateUrl: './fancy-input.component.html',
  styleUrls: ['./fancy-input.component.scss']
})
export class FancyInputComponent implements OnInit, AfterViewInit {

  private static id_generator: number = 0;

  @Input() type: string;
  @Input() placeholder: string;
  @Input() name: string;
  @Input() defaultColor: string;
  @Input() activeColor: string;

  private inputId: string;

  constructor() {}

  ngOnInit() {
    this.defaultColor = this.defaultColor || '#495057';
    this.activeColor = this.activeColor || '#495057';
    this.type = this.type || 'text';
    this.inputId = 'fancy-input-field-identifier-' + FancyInputComponent.id_generator++;
  }

  ngAfterViewInit(): void {
    let input = $('input#' + this.inputId);
    let inputContainer = input.parent();

    inputContainer.css('--default-color', this.defaultColor);
    inputContainer.css('--active-color', this.activeColor);

    input.on('change focus keyup blur', () => {
      let isActive = input.is(':focus') || input.val() !== '';
      inputContainer.toggleClass('active-input', isActive)
    });
  }

}
