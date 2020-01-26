/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PrivateMessageService } from './private-message.service';

describe('Service: PrivateMessage', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PrivateMessageService]
    });
  });

  it('should ...', inject([PrivateMessageService], (service: PrivateMessageService) => {
    expect(service).toBeTruthy();
  }));
});
