import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Staff } from '../model/staff.model';
import { StaffService } from '../services/staff.service';

const expression: RegExp = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;

@Component({
  selector: 'app-staff-profile',
  templateUrl: './staff-profile.component.html',
  styleUrls: ['./staff-profile.component.css']
})
export class StaffProfileComponent implements OnInit {

  public staff: Staff = new Staff;
  public bottonPressed: boolean = false;
  public oldPassword: string = "";
  public newPassword: string = "";
  public confirmPassword: string = "";
  public maxDate: Date = new Date();
  constructor(private staffService: StaffService, private router: Router) { }

  ngOnInit(): void {
    this.staffService.getById(2011).subscribe(res => {
      this.staff = res;
    })
  }

  public update(staff: Staff): void {
    if (this.isValidInput())
    {
      if(this.bottonPressed){
        staff.password = this.newPassword;
      }
      this.staffService.update(staff).subscribe(res => {
        alert("Updated successfully!")
        this.router.navigate(['/staff-profile']);
       
      });
    }
  }
  public changePassword(): void {
    if(this.bottonPressed)
      this.bottonPressed = false 
    else 
      this.bottonPressed = true
  }

  private isValidInput(): boolean {
    if (!(this.staff?.firstName != '' && this.staff?.lastName != ''
    && this.staff?.city != '' && this.staff?.country != ''
    && this.staff?.number != '' && this.staff?.street != ''
    && this.staff?.firstName != '' && this.staff?.lastName != ''
    && this.staff?.phoneNumber != '' && this.staff?.email != ''
    && this.staff?.dob.toString() != '' && this.staff?.personalId.toString() != '')) {
      alert("Field cannot be empty!");
      return false;
    } else if(!expression.test(this.staff?.email)){
      alert("Email format is not correct!")
      return false
    }  else if (this.bottonPressed && !(this.oldPassword != "" && this.newPassword != "" && this.confirmPassword != "")){
      alert("Fields cannot be empty!")
      return false;
    }else if (this.bottonPressed && this.oldPassword != this.staff.password){
      alert("Incorrect old password!")
      return false;
    } else if (this.bottonPressed && this.newPassword != this.confirmPassword){
      alert("Passwords do not match!")
      return false;
    } 
    else 
      return true;
    }

}
