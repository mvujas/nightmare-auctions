import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormsModule, Validators, FormBuilder } from '@angular/forms';
import { UserService } from '@app/core/http/user/user.service';
import deepEqual from 'deep-equal';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['../../shared-scss/authentication-pages-styles.scss']
})
export class RegistrationPageComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private userService: UserService) { }

  private alert = null;

  private submitted: boolean;
  private registrationForm: FormGroup;

  private previousFormValue = null;

  get f() { return this.registrationForm.controls; }

  ngOnInit(): void {
    this.submitted = false;

    this.registrationForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(32), Validators.pattern(/^[\w\d]+$/)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', Validators.required]
    }, {
      validators: [
        this.checkPasswordsEquality.bind(this),
        this.checkPasswordPattern.bind(this)
      ]
    });
  }

  checkPasswordsEquality(form: FormGroup) {
    let passwordControl = form.controls.password;
    let confirmPasswordControl = form.controls.confirmPassword;

    confirmPasswordControl.setErrors(
      (passwordControl.value !== confirmPasswordControl.value) ? 
        { mustMatch: true } : null
    );
  }

  checkPasswordPattern(form: FormGroup) {
    let passwordControl = form.controls.password;
    let password = passwordControl.value;

    if(!passwordControl.errors) {
      let regExps = [
        {
          regex: /^.*[a-z].*$/g,
          errorObj: { noSmallLetter: true }
        },
        {
          regex: /^.*[A-Z].*$/g,
          errorObj: { noLargeLetter: true }
        },
        {
          regex: /^.*\d.*$/g,
          errorObj: { noDigit: true }
        },
        {
          regex: /^\S*$/g,
          errorObj: { whitespaces: true }
        }
      ];
  
      let errorObj = null;
  
      for(let i: number = 0; i < regExps.length && !errorObj; i++) {
        let regex = regExps[i]; 
        let ok = regex.regex.test(password);
        if(!ok) {
          errorObj = regex.errorObj;
        }
      }
      
      passwordControl.setErrors(errorObj);
    }
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

  attemptRegistration() {
    this.submitted = true;
    if(this.registrationForm.valid) {
      let formValue = this.registrationForm.value;

      if(!deepEqual(
          formValue, this.previousFormValue)) {
        this.previousFormValue = formValue;

        this.userService.registerUser({
          username: formValue.username,
          email: formValue.email,
          password: formValue.password
        }).subscribe(
          this.successfulRegistration.bind(this),
          this.registrationError.bind(this)
        );
      }
    }
  }

  successfulRegistration() {
    this.showSuccessMessage('Successful registration');
    this.submitted = false;
    this.registrationForm.reset();
  }

  registrationError(res) {
    this.showErrorMessage('Registration error');
  }

}
