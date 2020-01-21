import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormsModule, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['../../shared-scss/authentication-pages-styles.scss']
})
export class RegistrationPageComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }

  alert = null;

  submitted: boolean;
  registrationForm: FormGroup;

  get f() { return this.registrationForm.controls; }

  ngOnInit(): void {
    this.submitted = false;

    this.registrationForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    }, {
      validators: [this.checkPasswords.bind(this)]
    });
  }

  checkPasswords(form: FormGroup) {
    let passwordControl = form.controls.password;
    let confirmPasswordControl = form.controls.confirmPassword;

    confirmPasswordControl.setErrors(
      (passwordControl.value !== confirmPasswordControl.value) ? 
        { mustMatch: true } : null
    );
  }

  showErrorMessage(message) {
    this.alert = {
      type: 'danger',
      message: message
    }
  }

  closeAlert() {
    this.alert = null;
  }

  attemptRegistration() {
    this.submitted = true;
    let formValue = this.registrationForm.value;

    console.log(this.registrationForm.valid);
    console.log(formValue);
  }

}
