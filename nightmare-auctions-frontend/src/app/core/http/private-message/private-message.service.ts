import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '@app/shared/model/user';
import { PrivateMessage } from '@app/shared/model/private-message';

@Injectable({
  providedIn: 'root'
})
export class PrivateMessageService {

  constructor(private http: HttpClient) { }

  public getAllChaters(username: string): Observable<User[]> {
    return this.http.get<User[]>(`api/message/${username}`);
  }

  public getMessagesBetweenUsers(
      username1: string, username2: string): Observable<PrivateMessage[]> {
    return this.http.get<PrivateMessage[]>(`api/message/${username1}/${username2}`);
  }

  public sendMessage(receiver: string, text: string) {
    return this.http.post(`api/message/${receiver}`, { text: text });
  }

}
