import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from '@app/shared/model/item';
import { Observable } from 'rxjs';
import { Page } from '@app/shared/domain/page';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  public getAll(): Observable<Page<Item>> {
    return this.http.get<Page<Item>>('api/item', );
  }

  public getAllFiltered(queryParams: string): Observable<Page<Item>> {
    return this.http.get<Page<Item>>(`api/item?${queryParams}`);
  }

  public saveItem(item: Item) {
    return this.http.post('api/item', item);
  }

  public getById(id: number): Observable<Item> {
    return this.http.get<Item>(`api/item/${id}`);
  }

  public placeBid(id: number, price: number) {
    return this.http.post(`api/item/${id}/bid`, { price: price });
  }

  public endAuction(id: number) {
    return this.http.post(`api/item/${id}/end`, {});
  }

}
