/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { LoginService } from './login.service';

describe('Service: LoginService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [Loginervice]
    });
  });

  it('should ...', inject([LoginServiceService], (service: LoginService) => {
    expect(service).toBeTruthy();
  }));
});
