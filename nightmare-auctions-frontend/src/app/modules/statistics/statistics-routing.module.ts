import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SoldItemsStatisticsComponent } from './pages/sold-items-statistics/sold-items-statistics.component';
import { AuthGuard } from '@app/core/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: SoldItemsStatisticsComponent,
    canActivate: [AuthGuard],
    data: {
      authorisedOnly: true
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StatisticsRoutingModule { }
