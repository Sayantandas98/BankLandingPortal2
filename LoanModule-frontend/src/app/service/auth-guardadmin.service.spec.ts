import { TestBed } from '@angular/core/testing';

import { AuthGuardadminService } from './auth-guardadmin.service';

describe('AuthGuardadminService', () => {
  let service: AuthGuardadminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuardadminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
