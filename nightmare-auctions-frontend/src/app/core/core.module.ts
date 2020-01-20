import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { FooterComponent } from './footer/footer.component';
import { FancyInputComponent } from '@app/shared/components/fancy-input/fancy-input.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ApiPrefixInterceptor } from './interceptors/api-prefix.interceptor';
import { GreetingService } from './http/greeting/greeting.service';
import { ErrorHandlerInterceptor } from './interceptors/error-handler.interceptor';
import { TokenInterceptor } from './interceptors/token.interceptor';

const componentsToExport: any[] = [
  NavigationBarComponent,
  FooterComponent
];

const sharedComponentsUsed: any[] = [
  FancyInputComponent
];

const interceptors: any[] = [
  ApiPrefixInterceptor,
  ErrorHandlerInterceptor,
  TokenInterceptor
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule
  ],
  declarations: [ 
    ...componentsToExport,
    ...sharedComponentsUsed
  ],
  exports: [ ...componentsToExport ],
  providers: [
    ...interceptors.map(interceptor => ({
      provide: HTTP_INTERCEPTORS,
      useClass: interceptor,
      multi: true
    }))
  ]
})
export class CoreModule { }
