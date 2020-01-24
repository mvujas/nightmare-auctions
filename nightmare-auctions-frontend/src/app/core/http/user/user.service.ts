import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '@app/shared/model/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public registerUser(body) {
    return this.http.post('api/user', body);
  }

  public getByUsername(username: string): Observable<User> {
    return this.http.get<User>(`api/user/${username}`);
  }

}
