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
  public getDate(): string {
    let date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    return `${year}-${month < 9 ? ('0'+month) : month}-${day < 9 ? ('0'+day) : day}`;
  }

  userRegister(){
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
    },error=>console.log(error.message));
  
  }
}
