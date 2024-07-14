import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanApplicationByIdComponent } from './loan-application-by-id.component';

describe('LoanApplicationByIdComponent', () => {
  let component: LoanApplicationByIdComponent;
  let fixture: ComponentFixture<LoanApplicationByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoanApplicationByIdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoanApplicationByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
