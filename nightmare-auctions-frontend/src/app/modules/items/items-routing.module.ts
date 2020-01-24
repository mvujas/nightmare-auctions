import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllItemsPageComponent } from './pages/all-items-page/all-items-page.component';
import { AddItemPageComponent } from './pages/add-item-page/add-item-page.component';
import { SingleItemPageComponent } from './pages/single-item-page/single-item-page.component';

const routes: Routes = [
  {
    path: '',
    component: AllItemsPageComponent
  },
  {
    path: 'add',
    component: AddItemPageComponent
  },
  {
    path: ':id',
    component: SingleItemPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemsRoutingModule { }
