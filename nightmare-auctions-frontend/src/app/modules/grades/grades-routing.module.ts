import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WaitingGradesPageComponent } from './pages/waiting-grades-page/waiting-grades-page.component';

const routes: Routes = [
  {
    path: 'waiting',
    component: WaitingGradesPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GradesRoutingModule { }
