import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PullNewMonthlyLoanApplicationsComponentComponent } from './pull-new-monthly-loan-applications-component.component';

describe('PullNewMonthlyLoanApplicationsComponentComponent', () => {
  let component: PullNewMonthlyLoanApplicationsComponentComponent;
  let fixture: ComponentFixture<PullNewMonthlyLoanApplicationsComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PullNewMonthlyLoanApplicationsComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PullNewMonthlyLoanApplicationsComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
