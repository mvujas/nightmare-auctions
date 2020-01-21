/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { RedirectUrlService } from './redirect-url.service';

describe('Service: RedirectUrl', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RedirectUrlService]
    });
  });

  it('should ...', inject([RedirectUrlService], (service: RedirectUrlService) => {
    expect(service).toBeTruthy();
  }));
});
