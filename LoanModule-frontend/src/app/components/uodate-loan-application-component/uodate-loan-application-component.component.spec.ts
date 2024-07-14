import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UodateLoanApplicationComponentComponent } from './uodate-loan-application-component.component';

describe('UodateLoanApplicationComponentComponent', () => {
  let component: UodateLoanApplicationComponentComponent;
  let fixture: ComponentFixture<UodateLoanApplicationComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UodateLoanApplicationComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UodateLoanApplicationComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
