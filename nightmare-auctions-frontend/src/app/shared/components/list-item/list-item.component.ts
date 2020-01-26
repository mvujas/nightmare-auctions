import { Component, OnInit, Input } from '@angular/core';
import { Item } from '@app/shared/model/item';

@Component({
  selector: 'app-list-item',
  templateUrl: './list-item.component.html',
  styleUrls: ['./list-item.component.scss']
})
export class ListItemComponent implements OnInit {

  constructor() { }

  @Input('item')
  private item: Item;

  ngOnInit() {
    if(this.item === null) {
      throw new Error("Item is not passed to List item");
    }
  }

}
