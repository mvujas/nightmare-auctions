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

}
