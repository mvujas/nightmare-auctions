import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@app/core/authentication/authentication.service';
import { FormControl, FormGroup } from '@angular/forms';

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

  constructor(private authService: AuthenticationService) { }


  attemptLogin(formValue): void {
    console.log('Pera!')
    this.authService.login(formValue).subscribe(
      console.log,
      console.log
    );
  }

}
