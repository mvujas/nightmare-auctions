import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private chatOpenerSubject = new Subject<string>();

  constructor() { }

  public waitForRequest(): Observable<string> {
    return this.chatOpenerSubject.asObservable();
  }

  public openChat(username: string) {
    this.chatOpenerSubject.next(username);
  }

}
