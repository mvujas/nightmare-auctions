import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@app/core/authentication/authentication.service';
import { FormControl, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { RedirectUrlService } from '@app/core/services/redirect-url.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['../../shared-scss/authentication-pages-styles.scss']
})
export class LoginPageComponent {

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  private params = null;

  constructor(private authService: AuthenticationService, 
      private redirectUrlService: RedirectUrlService, 
      private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => this.params = params);
  }

  alert = null;

  showErrorMessage(message) {
    this.alert = {
      type: 'danger',
      message: message
    }
  }

  closeAlert() {
    this.alert = null;
  }

  attemptLogin(formValue): void {
    this.authService.login(formValue).subscribe(
      this.handleSuccessfulLogin.bind(this),
      () => this.showErrorMessage('Invalid credentials')
    );
  }

  handleSuccessfulLogin() {
    this.redirectUrlService.redirectToUrl(this.params['redirectUrl']);
  }

}
