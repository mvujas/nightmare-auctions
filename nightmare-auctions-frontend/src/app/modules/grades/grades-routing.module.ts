import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WaitingGradesPageComponent } from './pages/waiting-grades-page/waiting-grades-page.component';
import { AuthGuard } from '@app/core/guards/auth.guard';

const routes: Routes = [
  {
    path: 'waiting',
    component: WaitingGradesPageComponent,
    canActivate: [AuthGuard],
    data: {
      authorisedOnly: true
    }
  },
  {
    path: '**',
    redirectTo: '/404'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GradesRoutingModule { }
