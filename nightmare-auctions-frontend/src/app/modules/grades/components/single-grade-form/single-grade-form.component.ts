import { Component, OnInit, Input, Output } from '@angular/core';
import { GradeHolder } from '@app/shared/model/grade-holder';
import { GradeService } from '@app/core/http/grade/grade.service';
import { FormBuilder, Validators } from '@angular/forms';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-single-grade-form',
  templateUrl: './single-grade-form.component.html',
  styleUrls: ['./single-grade-form.component.scss']
})
export class SingleGradeFormComponent implements OnInit {

  @Input()
  private gradeHolder: GradeHolder;

  @Output()
  private successfulSubmitCallback = new EventEmitter();

  private waitingGradeForm = null;
  private previousGradeValue = null;
  private submitted = false;
  private alert = null;

  constructor(private gradeService: GradeService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    if(this.gradeHolder == null) {
      throw new Error('Grade holder in SingleGradeFormComponent cannot be empty');
    }

    this.waitingGradeForm = this.formBuilder.group({
      value: ['', Validators.required]
    });

    console.log(this.gradeHolder);
  }

  showErrorMessage(message) {
    this.alert = {
      type: 'danger',
      message: message
    }
  }

  showSuccessMessage(message) {
    this.alert = {
      type: 'success',
      message: message
    }
  }

  closeAlert() {
    this.alert = null;
  }

  handleFormSubmit() {
    this.submitted = true;
    if(this.waitingGradeForm.valid) {
      let gradeValue = +this.waitingGradeForm.value.value;
      if(gradeValue != this.previousGradeValue) {
        this.previousGradeValue = gradeValue;

        this.saveGrade(gradeValue);
      }
    }
  }

  saveGrade(value) {
    this.gradeService.saveGradeValue(this.gradeHolder.id, value).subscribe(
      this.successfullySavedGrade.bind(this),
      this.failedToSaveGrade.bind(this)
    );
  }

  successfullySavedGrade(res) {
    this.successfulSubmitCallback.emit();
  }

  failedToSaveGrade(res) {
    this.showErrorMessage("There was an error trying to save grade");
  }

}
