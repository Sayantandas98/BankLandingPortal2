import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SanctionsListComponent } from './sanctions-list.component';

describe('SanctionsListComponent', () => {
  let component: SanctionsListComponent;
  let fixture: ComponentFixture<SanctionsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SanctionsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SanctionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
