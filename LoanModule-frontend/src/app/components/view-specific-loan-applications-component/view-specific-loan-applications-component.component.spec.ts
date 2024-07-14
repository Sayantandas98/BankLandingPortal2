import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSpecificLoanApplicationsComponentComponent } from './view-specific-loan-applications-component.component';

describe('ViewSpecificLoanApplicationsComponentComponent', () => {
  let component: ViewSpecificLoanApplicationsComponentComponent;
  let fixture: ComponentFixture<ViewSpecificLoanApplicationsComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewSpecificLoanApplicationsComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewSpecificLoanApplicationsComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
