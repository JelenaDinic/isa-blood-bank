import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BloodBankCenter } from '../model/blood-bank-center.model';
import { Staff } from '../model/staff.model';
import { BloodBankService } from '../services/blood-bank-center.service';
import { StaffService } from '../services/staff.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-staff-registration',
  templateUrl: './staff-registration.component.html',
  styleUrls: ['./staff-registration.component.css']
})
export class StaffRegistrationComponent implements OnInit {

  public staff: Staff = new Staff();
  public bloodbanks: BloodBankCenter[] = [];
  public errorMessage: Error = new Error;
  public errorMap: Map<string, string> = new Map();

  constructor(private bloodBankService: BloodBankService, private staffService: StaffService, private router: Router,private toastr: ToastrService) { }

  ngOnInit(): void {
    this.bloodBankService.getAll().subscribe(result => {
      this.bloodbanks = result;
      console.log(this.bloodbanks);
    })
  }

  public registerStaff() {

    if(this.isInputValid()) {
      this.staffService.create(this.staff).subscribe(res => 
      {
        this.router.navigate(['/']);
      }, (error) => {
        console.log(error)
        this.errorMessage = error;
        this.toastError();
      });
    }
    
  }

  public isInputValid(): boolean {

    if(this.staff.personalId.toString().length != 13) {
      alert('Personal id must be exactly 13 digits!');
      return false;
    }

    if(this.staff.phoneNumber.length < 9) {
      alert('Phone number must be at least 9 digits long')
      return false;
    }

    var regexp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;     
    // Converting the email to lowercase
    if(!regexp.test(String(this.staff.email).toLowerCase())) {
      alert('email format is not valid')
      return false;
    }

    if (this.staff.firstName.trim() == '' || this.staff.lastName.trim() == '' || this.staff.email.trim() == ''
    || this.staff.password.trim() == '' || this.staff.phoneNumber.trim() == '' || this.staff.dob == null || this.staff.street.trim() == ''
    || this.staff.number.trim() == '' || this.staff.city.trim() == '' || this.staff.country.trim() == '' || this.staff.bloodBankId == null 
    || this.staff.personalId == null || this.staff.gender.trim() == '') {
        alert('Please fill in all fields!');
        return false;
     }

     return true;
  }

  private toastError() {
    if (String(this.errorMessage).includes('406')){
      var error = localStorage.getItem('errormap')!;
      this.errorMap = new Map(JSON.parse(error));

      for (let entry of this.errorMap.entries()) {
        alert('Validation error: ' + entry[1]);
      }
    }
    else{
      alert(this.errorMessage.message);
    }
  }

}
