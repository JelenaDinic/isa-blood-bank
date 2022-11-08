import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBloodBankCenterComponent } from './update-blood-bank-center.component';

describe('UpdateBloodBankCenterComponent', () => {
  let component: UpdateBloodBankCenterComponent;
  let fixture: ComponentFixture<UpdateBloodBankCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateBloodBankCenterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateBloodBankCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
