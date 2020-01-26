import { Component, OnInit } from '@angular/core';
import { CategoryService } from '@app/core/http/category/category.service';
import { take } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import deepEqual from 'deep-equal';
import { ItemService } from '@app/core/http/item/item.service';

@Component({
  selector: 'app-add-item-page',
  templateUrl: './add-item-page.component.html',
  styleUrls: ['./add-item-page.component.scss']
})
export class AddItemPageComponent implements OnInit {

  constructor(
    private itemService: ItemService,
    private categoryService: CategoryService,
    private formBuilder: FormBuilder) { }

  private newItemForm: FormGroup;
  private categoriesResult = null;
  private lastSubmission = null;
  private submitted = false;
  private alert = null;

  ngOnInit() {
    this.categoryService
      .getAll()
      .subscribe(this.initializeForm.bind(this), 
      () => this.categoriesResult = { success: false });
  }

  private get f() {
    return this.newItemForm.controls;
  }

  initializeForm(categories) {
    if(categories.length === 0) {
      this.categoriesResult = { success: false }
    }
    else {
      this.newItemForm = this.formBuilder.group({
        name: ['', [Validators.required, Validators.pattern(/^.*\S.*$/)]],
        startingPrice: ['', [Validators.required, Validators.min(1)]],
        categoryName: ['', Validators.required],
        details: ['']
      });
  
      this.categoriesResult = {
        success: true,
        data: categories
      };
    }
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

  /*handleFormSubmission() {
    this.submitted = true;
    if(this.newItemForm.valid) {
      let formValue = this.newItemForm.value
      if(!deepEqual(formValue, this.lastSubmission)) {
        this.lastSubmission = formValue;
 
        this.processValue(formValue);
      }
    }
  }

  processValue(formValue) {

  }*/

  handleFormSubmission() {
    this.submitted = true;
    if(this.newItemForm.valid) {
      let formValue = this.newItemForm.value
      if(!deepEqual(formValue, this.lastSubmission)) {
        this.lastSubmission = formValue;

        this.processValue(formValue);
      }
    }
  }

  processValue(formValue) {
    this.itemService.saveItem(formValue).subscribe(
      this.successfulSave.bind(this),
      this.failedSave.bind(this)
    );
  }

  failedSave() {
    this.showErrorMessage("There was an error trying to create item. Please try again later");
  }

  successfulSave() {
    this.submitted = false;
    this.newItemForm.reset();
    this.showSuccessMessage("Item is saved successfully");
  }

}
