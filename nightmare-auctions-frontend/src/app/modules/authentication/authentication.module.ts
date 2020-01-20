import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationRoutingModule } from './authentication-routing.module';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import { FancyInputWithUnderlineComponent } from '@app/shared/components/fancy-input/fancy-input-with-underline/fancy-input-with-underline.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const components: any[] = [
  LoginPageComponent,
  RegistrationPageComponent
];

const sharedComponentsUsed: any[] = [
  FancyInputWithUnderlineComponent
];

@NgModule({
  imports: [
    CommonModule,
    AuthenticationRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [ 
    ...components, 
    ...sharedComponentsUsed 
  ]
})
export class AuthenticationModule { }
