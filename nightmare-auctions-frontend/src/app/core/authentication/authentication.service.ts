import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Subject, Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loggedIn: BehaviorSubject<boolean>;

  constructor(private http: HttpClient) {
    this.loggedIn = new BehaviorSubject<boolean>(this.token != null);
  }

  private updateLoggedIn() {
    this.loggedIn.next(this.token != null);
  }

  public login(credentials) {
    return this.http
            .post("login", credentials, {observe: 'response'})
            .pipe(
              map(this.handleSuccessfulLogin.bind(this))
            );
  }

  private handleSuccessfulLogin(response) {
    let authorizationHeader = response.headers.get('Authorization');
    if(authorizationHeader == null) {
      throw Error('Authorization header cannot be null!');
    }

    let bearerHeader = /^Bearer (.*)$/;
    let textTokens = bearerHeader.exec(authorizationHeader);
    if(textTokens == null || textTokens.length < 2) {
      throw Error('Invalid token format!');
    }
    let token = textTokens[1];

    localStorage.setItem('token', token);
    this.updateLoggedIn();
  }

  public logout(): void {
    localStorage.removeItem('token');
    this.updateLoggedIn();
  }

  public get token(): string {
    return localStorage.getItem('token');
  }

  public get isAuthenticated(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

}
