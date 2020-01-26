import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRoutingModule } from './user-routing.module';
import { SingleUserPageComponent } from './pages/single-user-page/single-user-page.component';
import { RatingDisplayComponent } from '@app/shared/components/rating-display/rating-display.component';
import { SharedModule } from '@app/shared/shared.module';

const components: any[] = [
  SingleUserPageComponent
];

@NgModule({
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModule
  ],
  declarations: [ ...components ]
})
export class UserModule { }
