import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatisticsRoutingModule } from './statistics-routing.module';
import { SoldItemsStatisticsComponent } from './pages/sold-items-statistics/sold-items-statistics.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SelectPeriodFormComponent } from './components/select-period-form/select-period-form.component';

const components: any[] = [
  SoldItemsStatisticsComponent,
  SelectPeriodFormComponent
];

@NgModule({
  imports: [
    CommonModule,
    StatisticsRoutingModule,
    NgbModule
  ],
  declarations: [ 
    ...components
  ]
})
export class StatisticsModule { }
