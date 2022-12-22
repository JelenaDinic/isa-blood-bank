import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplaintsDisplayComponent } from './complaints-display.component';

describe('ComplaintsDisplayComponent', () => {
  let component: ComplaintsDisplayComponent;
  let fixture: ComponentFixture<ComplaintsDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComplaintsDisplayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComplaintsDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
