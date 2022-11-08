import { Component, OnInit } from '@angular/core';
import { Staff } from '../model/staff.model';

@Component({
  selector: 'app-staff-registration',
  templateUrl: './staff-registration.component.html',
  styleUrls: ['./staff-registration.component.css']
})
export class StaffRegistrationComponent implements OnInit {

  public staff: Staff = new Staff();

  constructor() { }

  ngOnInit(): void {
  }

}
