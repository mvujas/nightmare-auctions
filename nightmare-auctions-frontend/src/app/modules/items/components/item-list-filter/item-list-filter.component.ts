import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { filter, take } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from '@app/core/http/category/category.service';
import { Category } from '@app/shared/model/category';
import { BehaviorSubject, Observable } from 'rxjs';
import { SearchItemsValueHolder } from '../../domain/search-items-value-holder';

@Component({
  selector: 'app-item-list-filter',
  templateUrl: './item-list-filter.component.html',
  styleUrls: ['./item-list-filter.component.scss']
})
export class ItemListFilterComponent implements OnInit {

  constructor(
      private formBuilder: FormBuilder, 
      private route: ActivatedRoute,
      private categoryService: CategoryService) {
    // maybe this comes handy eventually
    this.route.queryParams
      .pipe(take(1))
      .subscribe(params => this.params = params);
  }
  
  private params = null;
  private filterForm: FormGroup;
  private categories$: Category[];
  private formValueSubject: BehaviorSubject<SearchItemsValueHolder>;
  private avaliablePageSizes: number[] = [10, 25, 40, 60];

  private maxPages: number;

  private range(n: number) {
    return new Array(n);
  }

  ngOnInit() {
    this.createForm();

    this.updatePages(0, 1);
    this.formValueSubject = new BehaviorSubject<SearchItemsValueHolder>(
      this.formValueToSearchItemsValueHolder(this.filterForm.value));

    this.loadCategories();
  }

  private createForm() {
    this.filterForm = this.formBuilder.group({
      name: '',
      category: '',
      sortCriteria: '',
      sortStyle: 'asc',
      minimumPrice: 0,
      maximumPrice: 100000,
      itemsPerPage: "10",
      pageNumber: "10"
    });
  }

  private loadCategories() {
    this.categoryService
      .getAll()
      .pipe(take(1))
      .subscribe(
        categories => this.categories$ = categories
      );
  }

  private get f() {
    return this.filterForm.controls;
  }

  private handleSearch() {
    this.subjectValue = this.filterForm.value;
  }

  public updatePages(currentPage: number, maxPages: number) {
    this.filterForm.controls.pageNumber.setValue(currentPage);
    this.maxPages = maxPages;
  }

  private formValueToSearchItemsValueHolder(formValue): SearchItemsValueHolder {
    return {
      name: formValue.name,
      category: formValue.category,
      sortCriteria: formValue.sortCriteria,
      sortStyle: formValue.sortStyle,
      minimumPrice: formValue.minimumPrice,
      maximumPrice: formValue.maximumPrice,
      itemsPerPage: +formValue.itemsPerPage, // WHY JS WHY?! (if there's a bug here's a +, dont overlook it)
      pageNumber: +formValue.itemsPerPage
    };
  }

  private set subjectValue(formValue) {
    this.formValueSubject.next(
      this.formValueToSearchItemsValueHolder(formValue));
  }

  public get formValueObservable(): Observable<SearchItemsValueHolder> {
    return this.formValueSubject.asObservable();
  }

}
