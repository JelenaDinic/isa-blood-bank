import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  user: User = new User();
  constructor(private userService: UserService) { }

  ngOnInit(): void {

  }

  userRegister(){
    if(this.user.personalId.toString().length != 13) {
      alert('Personal id must be exactly 13 digits!');
      return;
    };

    if(this.user.phoneNumber.length < 9) {
      alert('Phone number must be at least 9 digits long')
      return;
    };

    var regexp = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;     
    // Converting the email to lowercase
    if(!regexp.test(String(this.user.email).toLowerCase())) {
      alert('email format is not valid')
      return;
    };

    if (this.user.firstName.trim() == '' || this.user.lastName.trim() == '' || this.user.email.trim() == '' || this.user.street.trim() == ''
    || this.user.password.trim() == '' || this.user.confirmPassword.trim() == '' || this.user.phoneNumber.trim() == '' || this.user.dob == null) {
        alert('Please fill in all fields!');
        return;
     };

    if(this.user.password.trim() != this.user.confirmPassword.trim()){
      alert("Passwords don't match")
      return;
    };

    console.log(this.user)
    this.userService.create(this.user).subscribe(data=>{
      alert("User successfully registered")
    },error=>alert("User is not registered"));
  
  }
}
