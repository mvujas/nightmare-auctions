import { Component, OnInit } from '@angular/core';
import { ItemService } from '@app/core/http/item/item.service';
import { Item } from '@app/shared/model/item';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import deepEqual from 'deep-equal';
import { UserAuthHolder } from '@app/shared/domain/user-auth-holder';
import { AuthenticationService } from '@app/core/authentication/authentication.service';

@Component({
  selector: 'app-single-item-page',
  templateUrl: './single-item-page.component.html',
  styleUrls: ['./single-item-page.component.scss']
})
export class SingleItemPageComponent implements OnInit {

  constructor(private itemService: ItemService,
    private authService: AuthenticationService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router) { }

  private currentUser: UserAuthHolder = null;
  private itemLoadingFailure: boolean = false;
  private item: Item = null;
  private bidForm: FormGroup = null;
  private previousBidFormValue = null;
  private bidFormSubmitted = null;
  private alert = null;

  ngOnInit() {
    this.authService.basicUserDetails.subscribe(value => this.currentUser = value);
    this.route.params.subscribe(this.handlePathParams.bind(this));
  }

  showErrorMessage(message) {
    this.alert = {
      type: 'danger',
      message: message
    }
  }

  showSuccessMessage(message) {
    this.alert = {
      type: 'success',
      message: message
    }
  }

  closeAlert() {
    this.alert = null;
  }

  handlePathParams(params) {
    let id: number = params.id;

    this.loadItem(id);
  }

  loadItem(id) {
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

    this.itemService.placeBid(this.item.id, formValue.price).subscribe(
      this.successfulBid.bind(this),
      this.failedBid.bind(this)
    )
  }

  successfulBid(item) {
    this.loadItem(this.item.id);

    this.bidForm.reset();
    this.bidFormSubmitted = false;

    this.showSuccessMessage("You have successfully placed your bid");
  }

  failedBid(res) {
    this.showErrorMessage("Failed to place the bid. Please try again later");
  }

  endAuction() {
    this.itemService.endAuction(this.item.id).subscribe(
      this.successfulEndOfAuction.bind(this),
      console.log
    );
  }

  successfulEndOfAuction(value) {
    this.showSuccessMessage("You have successfully ended auction");
    this.loadItem(this.item.id);
  }

  failedEndOfAuction(res) {
    this.showErrorMessage("Failed to end the auction. Please try again later");
  }

}
