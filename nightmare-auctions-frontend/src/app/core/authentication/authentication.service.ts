import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { assertNotNull } from '@angular/compiler/src/output/output_ast';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loggedIn: Subject<boolean> = new Subject<boolean>();

  constructor(private http: HttpClient) {
    this.updateLoggedIn();
  }

  private updateLoggedIn(): void {
    this.loggedIn.next(this.token != null);
  }

  public login(credentials) {
    return this.http
            .post("login", credentials, {observe: 'response'})
            .pipe(
              map(this.handleSuccessfulLogin)
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

  public get isAuthentication(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

}
