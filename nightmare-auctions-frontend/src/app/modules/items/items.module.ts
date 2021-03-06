import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemsRoutingModule } from './items-routing.module';
import { AllItemsPageComponent } from './pages/all-items-page/all-items-page.component';
import { ItemListFilterComponent } from './components/item-list-filter/item-list-filter.component';
import { ListItemComponent } from '../../shared/components/list-item/list-item.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddItemPageComponent } from './pages/add-item-page/add-item-page.component';
import { SingleItemPageComponent } from './pages/single-item-page/single-item-page.component';
import { SharedModule } from '@app/shared/shared.module';

const components: any[] = [
  AllItemsPageComponent,
  ItemListFilterComponent,
  AddItemPageComponent,
  SingleItemPageComponent
];

@NgModule({
  imports: [
    CommonModule,
    ItemsRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    NgbModule,
    SharedModule
  ],
  declarations: [ ...components ]
})
export class ItemsModule { }
