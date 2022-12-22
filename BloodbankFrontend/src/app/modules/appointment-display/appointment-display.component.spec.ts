import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentDisplayComponent } from './appointment-display.component';

describe('AppointmentDisplayComponent', () => {
  let component: AppointmentDisplayComponent;
  let fixture: ComponentFixture<AppointmentDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentDisplayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
