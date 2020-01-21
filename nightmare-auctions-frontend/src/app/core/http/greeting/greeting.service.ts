import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GreetingService {

  constructor(private http: HttpClient) { }

  greeting() {
    return this.http.get("api/greeting");
  }

  message() {
    return this.http.get("api/message");
  }

  login(username: string, password: string) {
    return this.http.post("login", {
      username: username,
      password: password
    });
  }

}
