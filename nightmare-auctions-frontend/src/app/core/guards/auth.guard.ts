import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from '../authentication/authentication.service';
import { take } from 'rxjs/operators';
import { Statement } from '@angular/compiler';
import { UserDetails } from '@app/shared/domain/UserDetails';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    
    constructor(private router: Router, private authService: AuthenticationService) {}
    
    readonly routeChecks:((ActivatedRouteSnapshot, RouterStateSnapshot, UserDetails) => boolean)[] = [
        this.checkForLoggedAccessBlock,
        this.checkForNonLoggedAccessBlock
    ];

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        let userDetails: UserDetails;
        this.authService.basicUserDetails
            .pipe(take(1))
            .subscribe(value => userDetails = value);

        let canUserAccess: boolean = true;
        for(let i: number = 0; i < this.routeChecks.length && canUserAccess; i++) {
            let routeCheckFunction = this.routeChecks[i].bind(this);
            let isUserAllowedForThisCheck = routeCheckFunction(route, state, userDetails);
            canUserAccess = canUserAccess && isUserAllowedForThisCheck;
        }

        return canUserAccess;
    }

    private checkForLoggedAccessBlock(
            route: ActivatedRouteSnapshot, 
            state: RouterStateSnapshot, 
            userDetails: UserDetails) : boolean {
        let isUserLoggedIn = userDetails !== null;

        let isBlocked: boolean = 
            isUserLoggedIn && route.data.nonAuthorisedOnly === true;

        if(isBlocked) {
            this.router.navigate(['/home']);
        }
        return !isBlocked;
    }

    private checkForNonLoggedAccessBlock(
        route: ActivatedRouteSnapshot, 
        state: RouterStateSnapshot, 
        userDetails: UserDetails) : boolean {
    let isUserLoggedIn = userDetails !== null;

    let isBlocked: boolean = 
        !isUserLoggedIn && route.data.authorisedOnly === true;

    if(isBlocked) {
        this.router.navigate(['/auth/login'], {
            queryParams: {
                returnUrl: state.url
            }
        });
    }
    return !isBlocked;
}

    
}
