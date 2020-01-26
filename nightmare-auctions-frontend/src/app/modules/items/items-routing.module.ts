import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllItemsPageComponent } from './pages/all-items-page/all-items-page.component';
import { AddItemPageComponent } from './pages/add-item-page/add-item-page.component';
import { SingleItemPageComponent } from './pages/single-item-page/single-item-page.component';
import { AuthGuard } from '@app/core/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: AllItemsPageComponent
  },
  {
    path: 'add',
    component: AddItemPageComponent,
    canActivate: [AuthGuard],
    data: {
      authorisedOnly: true
    }
  },
  {
    path: ':id',
    component: SingleItemPageComponent
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
export class ItemsRoutingModule { }
