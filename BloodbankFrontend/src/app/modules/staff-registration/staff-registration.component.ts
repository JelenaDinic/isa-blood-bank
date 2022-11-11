import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BloodBankCenter } from '../model/blood-bank-center.model';
import { Staff } from '../model/staff.model';
import { BloodBankService } from '../services/blood-bank-center.service';
import { StaffService } from '../services/staff.service';

@Component({
  selector: 'app-staff-registration',
  templateUrl: './staff-registration.component.html',
  styleUrls: ['./staff-registration.component.css']
})
export class StaffRegistrationComponent implements OnInit {

  public staff: Staff = new Staff();
  public bloodbanks: BloodBankCenter[] = [];

  constructor(private bloodBankService: BloodBankService, private staffService: StaffService, private router: Router) { }

  ngOnInit(): void {
    this.bloodBankService.getAll().subscribe(result => {
      this.bloodbanks = result;
      console.log(this.bloodbanks);
    })
  }

  public registerStaff() {
    if (this.staff.firstName.trim() == '' || this.staff.lastName.trim() == '' || this.staff.email.trim() == ''
    || this.staff.password.trim() == '' || this.staff.phoneNumber.trim() == '' || this.staff.dob == null) {
        alert('Please fill in all fields!');
        return;
     }
    this.staffService.create(this.staff).subscribe(res => {
      this.router.navigate(['/']);
    });
  }

}
