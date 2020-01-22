import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemsRoutingModule } from './items-routing.module';
import { AllItemsPageComponent } from './pages/all-items-page/all-items-page.component';
import { ItemListFilterComponent } from './components/item-list-filter/item-list-filter.component';
import { ListItemComponent } from './components/list-item/list-item.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

const components: any[] = [
  AllItemsPageComponent,
  ItemListFilterComponent,
  ListItemComponent
];

@NgModule({
  imports: [
    CommonModule,
    ItemsRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  declarations: [ ...components ]
})
export class ItemsModule { }
