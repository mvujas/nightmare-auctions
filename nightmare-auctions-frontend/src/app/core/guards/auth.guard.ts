import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from '../authentication/authentication.service';
import { take } from 'rxjs/operators';
import { Statement } from '@angular/compiler';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    
    constructor(private router: Router, private authService: AuthenticationService) {}
    
    readonly routeChecks:((ActivatedRouteSnapshot, RouterStateSnapshot, boolean) => boolean)[] = [
        this.checkForLoggedAccessBlock,
        this.checkForNonLoggedAccessBlock
    ];

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        let isUserLoggedIn: boolean;
        this.authService.isAuthenticated
            .pipe(take(1))
            .subscribe(value => isUserLoggedIn = value);

        let canUserAccess: boolean = true;
        for(let i: number = 0; i < this.routeChecks.length && canUserAccess; i++) {
            let routeCheckFunction = this.routeChecks[i].bind(this);
            let isUserAllowedForThisCheck = routeCheckFunction(route, state, isUserLoggedIn);
            canUserAccess = canUserAccess && isUserAllowedForThisCheck;
        }

        return canUserAccess;
    }

    private checkForLoggedAccessBlock(
            route: ActivatedRouteSnapshot, 
            state: RouterStateSnapshot, 
            isUserLoggedIn: boolean) : boolean {

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
        isUserLoggedIn: boolean) : boolean {

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
