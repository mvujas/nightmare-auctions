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

  private maxPages: number;

  private range(n: number) {
    return new Array(n);
  }

  ngOnInit() {
    this.createForm();

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
      maximumPrice: 100000
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

  private formValueToSearchItemsValueHolder(formValue): SearchItemsValueHolder {
    return {
      ...formValue
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
