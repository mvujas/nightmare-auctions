import { Component, OnInit, Input, AfterViewInit, forwardRef, HostListener } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
declare var $: any;

@Component({
  selector: 'app-fancy-input',
  templateUrl: './fancy-input.component.html',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => FancyInputComponent),
      multi: true
    }
  ],
  styleUrls: ['./fancy-input.component.scss']
})
export class FancyInputComponent implements OnInit, AfterViewInit, ControlValueAccessor {

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
    
    $(document).ready(() => this.checkActiveState(input, inputContainer)); 
    input.on('change focus keyup blur', () => this.checkActiveState(input, inputContainer)); // AUTOFILL SUCKS...
  }

  checkActiveState(input, inputContainer) {
    let isActive = input.is(':focus') || (input.val() != null && input.val().length > 0);
    inputContainer.toggleClass('active-input', isActive);
  }

  callActiveCheckWithDelay() {
    setTimeout((() => {
      let input = $('input#' + this.inputId);
      let inputContainer = input.parent();

      this.checkActiveState(input, inputContainer);
    }).bind(this), 100);
  }


  // EVERYTHING BENEATH IS A HUGE MESS!

    propagateChange = (_: any) => {};

    registerOnChange(fn: any): void {
      this.propagateChange = fn;
    }

    public textValue;

    public _value;

    addEvent($event) {
      this.value = this.textValue;
      this.propagateChange(this.value);
    }

    get value() {
      return this.textValue;
    }

    set value(val) {
      let empty: boolean = !val;
      if(empty) {
        val = '';
      }
      this._value = val;
      this.textValue = val;
      this.propagateChange(this._value);

      if(empty) {
        this.callActiveCheckWithDelay();
      }
    }

    writeValue(value: any): void {
      if (value !== undefined) {
        this.value = value;
        this.propagateChange(this.value);
      }
    }
    
    registerOnTouched(fn: any): void {}

    setDisabledState?(isDisabled: boolean): void {}

  //

}
