import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SysadminRegistrationComponent } from './sysadmin-registration.component';

describe('SysadminRegistrationComponent', () => {
  let component: SysadminRegistrationComponent;
  let fixture: ComponentFixture<SysadminRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SysadminRegistrationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SysadminRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
