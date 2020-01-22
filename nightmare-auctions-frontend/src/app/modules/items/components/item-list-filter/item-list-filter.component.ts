import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-item-list-filter',
  templateUrl: './item-list-filter.component.html',
  styleUrls: ['./item-list-filter.component.scss']
})
export class ItemListFilterComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) {}

  private filterForm: FormGroup;

  ngOnInit() {
    this.filterForm = this.formBuilder.group({
      name: '',
      category: '',
      sortCriteria: '',
      sortStyle: '',
      minimumPrice: 0,
      maximumPrice: 100000,
      itemsPerPage: "10",
      pageNumber: "10"
    });
  }

  private handleSearch() {
    console.log(this.filterForm.value);
  }

}
