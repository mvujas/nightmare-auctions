import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Subject, Observable, BehaviorSubject } from 'rxjs';
import { UserAuthHolder } from '@app/shared/domain/user-auth-holder';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private userDetails: BehaviorSubject<UserAuthHolder>;

  constructor(private http: HttpClient) {
    localStorage.removeItem('token');
    let storageUserDetails = JSON.parse(localStorage.getItem('userDetails'));
    this.userDetails = new BehaviorSubject<UserAuthHolder>(storageUserDetails);
  }

  private updateUserDetails(userDetails) {
    localStorage.setItem('userDetails', JSON.stringify(userDetails));
    this.userDetails.next(userDetails);
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

    this.updateUserDetails({ ...response.body });
  }

  public logout(): void {
    localStorage.removeItem('token');
    this.updateUserDetails(null);
  }

  public get token(): string {
    return localStorage.getItem('token');
  }

  public get basicUserDetails(): Observable<UserAuthHolder> {
    return this.userDetails.asObservable();
  }

}
