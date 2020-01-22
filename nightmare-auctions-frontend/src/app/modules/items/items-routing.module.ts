import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllItemsPageComponent } from './pages/all-items-page/all-items-page.component';

const routes: Routes = [
  {
    path: '',
    component: AllItemsPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemsRoutingModule { }
