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

  public getMessagesBetweenUsersSince(
      username1: string, username2: string, since: Date): Observable<PrivateMessage[]> {
    let queryString = '';
    if(since != null) {
      let year = since.getFullYear();
      let month = since.getMonth() + 1;
      let day = since.getDate();
      let hours = since.getHours();
      let minutes = since.getMinutes();
      let seconds = since.getSeconds();

      queryString = `?since=${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
    return this.http.get<PrivateMessage[]>(`api/message/${username1}/${username2}${queryString}`);
  }

  public sendMessage(receiver: string, text: string) {
    
    return this.http.post(`api/message/${receiver}`, { text: text });
  }

  public getMessagesBetweenUsers(username1: string, username2: string) {
    return this.getMessagesBetweenUsersSince(username1, username2, null);
  }

}
