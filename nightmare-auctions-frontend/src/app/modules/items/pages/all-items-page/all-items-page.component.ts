import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ItemListFilterComponent } from '../../components/item-list-filter/item-list-filter.component';
import { Observable } from 'rxjs';
import { Item } from '@app/shared/model/item';
import { ItemService } from '@app/core/http/item/item.service';
import { Page } from '@app/shared/domain/page';

@Component({
  selector: 'app-all-items-page',
  templateUrl: './all-items-page.component.html',
  styleUrls: ['./all-items-page.component.scss']
})
export class AllItemsPageComponent implements OnInit, AfterViewInit {
  

  @ViewChild('itemSearchFilter', { static: false })
  private searchFilter: ItemListFilterComponent;

  private itemsPage: Page<Item>;

  constructor(private itemService: ItemService) { }

  ngOnInit() {
    this.itemsPage = null;
  }

  ngAfterViewInit(): void {
    this.searchFilter.formValueObservable.subscribe(
      this.getItems.bind(this));
  }

  getItems(searchParams) {
    this.itemsPage = null;
    this.itemService.getAll().subscribe(
      value => this.itemsPage = value);
  }

}
