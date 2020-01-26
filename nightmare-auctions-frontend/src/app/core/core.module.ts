import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { FooterComponent } from './footer/footer.component';
import { FancyInputComponent } from '@app/shared/components/fancy-input/fancy-input.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ApiPrefixInterceptor } from './interceptors/api-prefix.interceptor';
import { ErrorHandlerInterceptor } from './interceptors/error-handler.interceptor';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '@app/shared/shared.module';
import { ChatComponent } from './chat/chat.component';

const componentsToExport: any[] = [
  NavigationBarComponent,
  FooterComponent,
  ChatComponent
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
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  declarations: [ 
    ...componentsToExport
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
