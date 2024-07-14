import { TestBed } from '@angular/core/testing';

import { LoanApplicationServiceService } from './loan-application-service.service';

describe('LoanApplicationServiceService', () => {
  let service: LoanApplicationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanApplicationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
