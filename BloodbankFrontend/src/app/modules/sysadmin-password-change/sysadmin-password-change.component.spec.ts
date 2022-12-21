import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SysadminPasswordChangeComponent } from './sysadmin-password-change.component';

describe('SysadminPasswordChangeComponent', () => {
  let component: SysadminPasswordChangeComponent;
  let fixture: ComponentFixture<SysadminPasswordChangeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SysadminPasswordChangeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SysadminPasswordChangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
