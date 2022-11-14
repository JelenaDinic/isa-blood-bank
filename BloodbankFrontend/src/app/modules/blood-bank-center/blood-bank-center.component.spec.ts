import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodBankCenterComponent } from './blood-bank-center.component';

describe('BloodBankCenterComponent', () => {
  let component: BloodBankCenterComponent;
  let fixture: ComponentFixture<BloodBankCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodBankCenterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodBankCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
