import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SingleUserPageComponent } from './pages/single-user-page/single-user-page.component';

const routes: Routes = [
  {
    path: ':username',
    component: SingleUserPageComponent
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
export class UserRoutingModule { }
