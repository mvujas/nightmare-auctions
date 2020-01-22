import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { filter, take } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-item-list-filter',
  templateUrl: './item-list-filter.component.html',
  styleUrls: ['./item-list-filter.component.scss']
})
export class ItemListFilterComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute) {
    // maybe this comes handy eventually
    this.route.queryParams
      .pipe(take(1))
      .subscribe(params => this.params = params);
  }
  
  private params = null;
  private filterForm: FormGroup;

  ngOnInit() {
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

  private get f() {
    return this.filterForm.controls;
  }

  private handleSearch() {
    console.log(this.filterForm.value);
  }

}
