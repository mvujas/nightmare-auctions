import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationRoutingModule } from './authentication-routing.module';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { FancyInputWithUnderlineComponent } from '@app/shared/components/fancy-input/fancy-input-with-underline/fancy-input-with-underline.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '@app/shared/shared.module';

const components: any[] = [
  LoginPageComponent,
  RegistrationPageComponent
];


@NgModule({
  imports: [
    CommonModule,
    AuthenticationRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    SharedModule
  ],
  declarations: [ 
    ...components
  ]
})
export class AuthenticationModule { }
