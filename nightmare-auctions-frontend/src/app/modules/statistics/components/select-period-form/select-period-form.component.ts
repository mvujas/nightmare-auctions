import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import deepEqual from 'deep-equal';
import { PeriodFormMessage } from '../../domain/period-form-message';
import { NgbDatepickerDayView } from '@ng-bootstrap/ng-bootstrap/datepicker/datepicker.module';

@Component({
  selector: 'app-select-period-form',
  templateUrl: './select-period-form.component.html',
  styleUrls: ['./select-period-form.component.scss']
})
export class SelectPeriodFormComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }

  private maxDate: NgbDateStruct = SelectPeriodFormComponent.dateToNgbDate(new Date());
  private minDate: NgbDateStruct = {
    day: 1,
    month: 1,
    year: 1990
  };

  private periodForm: FormGroup = null;

  private submitted: boolean = false;
  private lastSubmit: PeriodFormMessage = null;

  @Output()
  private submitCallback = new EventEmitter<PeriodFormMessage>();

  ngOnInit() {
    const emptyDate: NgbDateStruct = null;

    this.periodForm = this.formBuilder.group({
      from: [emptyDate, [Validators.required]],
      to: [emptyDate, [Validators.required]]
    }, {
      validators: [
        this.checkPasswordsEquality.bind(this)
      ]
    });
  }

  static ngbDateToDate(ngbDate: NgbDateStruct): Date {
    return new Date(ngbDate.year, ngbDate.month - 1, ngbDate.day);
  } 

  static dateToNgbDate(date: Date): NgbDateStruct {
    return {
      day: date.getDate(),
      month: date.getMonth() + 1,
      year: date.getFullYear()
    };
  } 

  checkPasswordsEquality(form: FormGroup) {
    let fromControl = form.controls.from;
    let toControl = form.controls.to;

    if(fromControl.value != null && toControl.value != null) {
      let fromDate = SelectPeriodFormComponent.ngbDateToDate(fromControl.value);
      let toDate = SelectPeriodFormComponent.ngbDateToDate(toControl.value);

      toControl.setErrors((toDate <= fromDate) ? { toNotHigher: true } : null);
    }
  }

  handleFormSubmission() {
    this.submitted = true;
    if(this.periodForm.valid) {
      let formValue = this.periodForm.value;
      let formDateValue: PeriodFormMessage = {
        from: SelectPeriodFormComponent.ngbDateToDate(formValue.from),
        to: SelectPeriodFormComponent.ngbDateToDate(formValue.to)
      };
      if(!deepEqual(formDateValue, this.lastSubmit)) {
        this.lastSubmit = formDateValue;
        this.submitCallback.emit(formDateValue);
      }
    }
  }

}
