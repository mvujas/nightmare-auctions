import { Component, OnInit } from '@angular/core';
import { ItemService } from '@app/core/http/item/item.service';
import { Item } from '@app/shared/model/item';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import deepEqual from 'deep-equal';

@Component({
  selector: 'app-single-item-page',
  templateUrl: './single-item-page.component.html',
  styleUrls: ['./single-item-page.component.scss']
})
export class SingleItemPageComponent implements OnInit {

  constructor(private itemService: ItemService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) { }

  private itemLoadingFailure: boolean = false;
  private item: Item = null;
  private bidForm: FormGroup = null;
  private previousBidFormValue = null;
  private bidFormSubmitted = null;

  ngOnInit() {
    this.route.params.subscribe(this.handlePathParams.bind(this));
  }

  handlePathParams(params) {
    let id: number = params.id;

    this.itemService.getById(id).subscribe(
      this.successfulLoadOfData.bind(this),
      this.failedToLoadItem.bind(this)
    );
  }

  failedToLoadItem(response) {
    if(response.status === 404) {
      this.router.navigate(['/404']);
    }
    else {
      this.itemLoadingFailure = true;
    }
  }

  successfulLoadOfData(item) {
    this.item = item;

    this.bidForm = this.formBuilder.group({
      price: ['', [Validators.required, Validators.min(item.price + 1)]]
    });
  }

  handleBidFormSubmit() {
    this.bidFormSubmitted = true;
    if(this.bidForm.valid) {
      let formValue = this.bidForm.value
      if(!deepEqual(formValue, this.previousBidFormValue)) {
        this.previousBidFormValue = formValue;

        this.processValue(formValue);
      }
    }
  }

  processValue(formValue: any) {
    console.log(formValue);
  }

}
