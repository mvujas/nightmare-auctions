import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ItemListFilterComponent } from '../../components/item-list-filter/item-list-filter.component';
import { Observable } from 'rxjs';
import { Item } from '@app/shared/model/item';
import { ItemService } from '@app/core/http/item/item.service';
import { Page } from '@app/shared/domain/page';
import deepEqual from 'deep-equal';
import { SearchItemsValueHolder } from '../../domain/search-items-value-holder';
import { DownloadService } from '@app/core/services/download.service';
import { AuthenticationService } from '@app/core/authentication/authentication.service';

@Component({
  selector: 'app-all-items-page',
  templateUrl: './all-items-page.component.html',
  styleUrls: ['./all-items-page.component.scss']
})
export class AllItemsPageComponent implements OnInit, AfterViewInit {
  

  @ViewChild('itemSearchFilter', { static: false })
  private searchFilter: ItemListFilterComponent;

  private NgbPagination

  private items: Item;
  private restResult;
  private previousSearchParams: SearchItemsValueHolder;
  private currentPage: number = 1;
  private collectionSize: number = 0;
  private pageSize: number = 12;

  private isUserLoggedIn: boolean = false; 

  constructor(
    private itemService: ItemService,
    private downloadService: DownloadService,
    private authService: AuthenticationService) { }

  ngOnInit() {
    this.items = null;
    this.restResult = null;

    this.authService.basicUserDetails.subscribe(
      userDetails => this.isUserLoggedIn = userDetails !== null)
  }

  ngAfterViewInit(): void {
    this.searchFilter.formValueObservable.subscribe(
      this.getItems.bind(this));
  }

  getItems(searchParams) {
    let changed = !deepEqual(
      this.previousSearchParams, searchParams);

    console.log("Changed: " + changed);

    if(changed) {
      this.previousSearchParams = searchParams;

      this.currentPage = 1;

      this.performItemRetrieval();
    }
  }

  searchItemsValueHolderToQueryParams(): string {
    let result: string = '';
    let searchItemsValueHolder = this.previousSearchParams

    for(let attr in searchItemsValueHolder) {
      if(attr != 'sortCriteria' && attr != 'sortStyle') {
        let value = searchItemsValueHolder[attr];
        if(value) {
          result += `&${attr}=${value}`;
        }
      }
    }

    if(searchItemsValueHolder.sortCriteria) {
      result += `&sort=${searchItemsValueHolder.sortCriteria},${searchItemsValueHolder.sortStyle}`;
    }

    return result;
  }

  performItemRetrieval() {
    let queryParams = `page=${this.currentPage - 1}&size=${this.pageSize}`;

    queryParams += this.searchItemsValueHolderToQueryParams();

    this.items = null;
    this.restResult = null;
    this.itemService.getAllFiltered(queryParams)
      .subscribe(
        this.handleDataReceive.bind(this),
        this.setErrorResult.bind(this));
  }

  handleDataReceive(value) {
    this.restResult = {
      success: true,
      items: value.content
    };
    this.items = value.content;
    this.setPagination(value.number + 1, value.totalElements)
  }

  setPagination(currentPage, collectionSize) {
    this.currentPage = currentPage;
    this.collectionSize = collectionSize;
  }

  onPageChanged(newPage) {
    this.currentPage = newPage;
    this.performItemRetrieval();
  }

  setErrorResult() {
    this.restResult = {
      success: false
    };
  }

  private downloadAlert;

  showErrorMessage(message) {
    this.downloadAlert = {
      type: 'danger',
      message: message
    }
  }

  showSuccessMessage(message) {
    this.downloadAlert = {
      type: 'success',
      message: message
    }
  }

  closeAlert() {
    this.downloadAlert = null;
  }

  downloadReport() {
    this.downloadService.downloadPDF('api/item/report', 'Ongoing auctions - Nightmare Auctions.pdf', (() => {
      this.showErrorMessage("There was an error trying to download file");
    }).bind(this));
  }

}
