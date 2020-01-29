import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from '@app/shared/model/item';
import { Observable } from 'rxjs';
import { Page } from '@app/shared/domain/page';
import { SoldItem } from '@app/shared/domain/sold-item';

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

  public getSoldItemsStatisticsInPeriod(
      username: string, from: Date, to: Date): Observable<SoldItem[]> {
    const dateToStr = (date: Date) => `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;

    console.log(dateToStr);
    
    let queryString: string = `after=${dateToStr(from)}&before=${dateToStr(to)}`
    return this.http.get<SoldItem[]>(`api/item/soldStatistics/${username}?${queryString}`);
  }

}
