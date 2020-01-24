import { Component, OnInit } from '@angular/core';
import { ItemService } from '@app/core/http/item/item.service';
import { Item } from '@app/shared/model/item';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-item-page',
  templateUrl: './single-item-page.component.html',
  styleUrls: ['./single-item-page.component.scss']
})
export class SingleItemPageComponent implements OnInit {

  constructor(private itemService: ItemService,
    private route: ActivatedRoute,
    private router: Router) { }

  private itemLoadingFailure: boolean = false;
  private item: Item = null;

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
  }

}
