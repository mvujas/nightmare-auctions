import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WaitingGradesPageComponent } from './pages/waiting-grades-page/waiting-grades-page.component';
import { GradesRoutingModule } from './grades-routing.module';
import { LoaderComponent } from '@app/shared/components/loader/loader.component';
import { SingleGradeFormComponent } from './components/single-grade-form/single-grade-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SharedModule } from '@app/shared/shared.module';

const components: any[] = [
  WaitingGradesPageComponent,
  SingleGradeFormComponent
];

@NgModule({
  imports: [
    CommonModule,
    GradesRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    SharedModule
  ],
  declarations: [ ...components ]
})
export class GradesModule { }
