import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRoutingModule } from './user-routing.module';
import { SingleUserPageComponent } from './pages/single-user-page/single-user-page.component';

const components: any[] = [
  SingleUserPageComponent
];

@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule
  ],
  declarations: [ ...components ]
})
export class UserModule { }
