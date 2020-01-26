import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RatingDisplayComponent } from './components/rating-display/rating-display.component';
import { FancyInputComponent } from './components/fancy-input/fancy-input.component';
import { FancyInputWithUnderlineComponent } from './components/fancy-input/fancy-input-with-underline/fancy-input-with-underline.component';
import { LoaderComponent } from './components/loader/loader.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ListItemComponent } from './components/list-item/list-item.component';
import { RouterModule } from '@angular/router';

const components: any[] = [
  RatingDisplayComponent,
  FancyInputComponent,
  FancyInputWithUnderlineComponent,
  LoaderComponent,
  ListItemComponent
];

@NgModule({
  imports: [
    CommonModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  declarations: [ ...components ],
  exports: [ ...components ]
})
export class SharedModule { }
