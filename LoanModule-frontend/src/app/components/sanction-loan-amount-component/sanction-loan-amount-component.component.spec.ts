import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SanctionLoanAmountComponentComponent } from './sanction-loan-amount-component.component';

describe('SanctionLoanAmountComponentComponent', () => {
  let component: SanctionLoanAmountComponentComponent;
  let fixture: ComponentFixture<SanctionLoanAmountComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SanctionLoanAmountComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SanctionLoanAmountComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
