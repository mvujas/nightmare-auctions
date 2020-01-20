import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { assertNotNull } from '@angular/compiler/src/output/output_ast';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {}

  public login(username: string, password: string) {
    let body = {
      username: username,
      password: password
    };
    return this.http
            .post("login", body, {observe: 'response'})
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
  }

  public get token(): string {
    return localStorage.getItem('token');
  }

  public get isAuthentication(): boolean {
    return this.token != null;
  }

}
