import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Sysadmin } from '../model/sysadmin.model';
import { SysadminService } from '../services/sysadmin.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-sysadmin-registration',
  templateUrl: './sysadmin-registration.component.html',
  styleUrls: ['./sysadmin-registration.component.css']
})
export class SysadminRegistrationComponent{

  public sysadmin: Sysadmin = new Sysadmin();
  public errorMessage: Error = new Error;
  public errorMap: Map<string, string> = new Map();

  constructor(private sysadminService: SysadminService, private router: Router,private toastr: ToastrService) { }


  public getDate(): string {
    let date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    return `${year}-${month < 9 ? ('0'+month) : month}-${day < 9 ? ('0'+day) : day}`;
  }

  public registerSysadmin() {

    if(this.isInputValid()) {
      this.sysadminService.create(this.sysadmin).subscribe(res => 
      {
        this.router.navigate(['/']);
        this.toastr.success("New system admin " + this.sysadmin.firstName + " successfully registered!");
      }, (error) => {
        console.log(error)
        this.errorMessage = error;
        this.toastError();
      });
    }
    
  }

  public isInputValid(): boolean {

    if(this.sysadmin.personalId.toString().length != 13) {
      alert('Personal id must be exactly 13 digits!');
      return false;
    }

    if(this.sysadmin.phoneNumber.length < 9) {
      alert('Phone number must be at least 9 digits long')
      return false;
    }

    var regexp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;     
    // Converting the email to lowercase
    if(!regexp.test(String(this.sysadmin.email).toLowerCase())) {
      alert('email format is not valid')
      return false;
    }

    if (this.sysadmin.firstName.trim() == '' || this.sysadmin.lastName.trim() == '' || this.sysadmin.email.trim() == ''
    || this.sysadmin.password.trim() == '' || this.sysadmin.phoneNumber.trim() == '' || this.sysadmin.dob == null || this.sysadmin.street.trim() == ''
    || this.sysadmin.number.trim() == '' || this.sysadmin.city.trim() == '' || this.sysadmin.country.trim() == ''
    || this.sysadmin.personalId == null || this.sysadmin.gender.trim() == '') {
        alert('Please fill in all fields!');
        return false;
     }

     if(!this.startsWithCapital(this.sysadmin.street)) {
      alert('Street must start with a capital letter!');
      return false;
    }

    if(!this.startsWithCapital(this.sysadmin.city)) {
      alert('City must start with a capital letter!');
      return false;
    }

    if(!this.startsWithCapital(this.sysadmin.country)) {
      alert('Country must start with a capital letter!');
      return false;
    }

    if(!this.sysadmin.number.match("([0-9]{1,3})[A-Z]?")) {
      alert('Invalid street number input!');
      return false;
    }

     return true;
  }

  public startsWithCapital(word: string){
    return word.charCodeAt(0) >= 65 && word.charCodeAt(0) <= 90
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
