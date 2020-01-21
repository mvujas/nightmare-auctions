import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class RedirectUrlService {

  constructor(private router: Router) { }

  public redirectToUrl(url) {
    const defaultLink = environment.defaultRedirectionUrl;

    let redirectUrl = url || defaultLink;
      
    console.log(redirectUrl);
    let isLink: boolean = /^(http|https):/g.test(redirectUrl);
    if(isLink) {
      window.location.href = redirectUrl;
    }
    else {
      this.router.navigate([ redirectUrl ]);
    }
  }

}
