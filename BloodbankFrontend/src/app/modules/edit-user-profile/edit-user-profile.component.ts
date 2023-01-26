import { Component, OnInit } from '@angular/core';
import { UserProfileDisplayDTO } from '../dto/userProfileDisplayDTO';
import { UserService } from '../services/user.service';
import { UserRegistrationComponent } from '../user-registration/user-registration.component';

@Component({
  selector: 'app-edit-user-profile',
  templateUrl: './edit-user-profile.component.html',
  styleUrls: ['./edit-user-profile.component.css']
})
export class EditUserProfileComponent implements OnInit {

  input_email : string = "";
  visible : boolean = false;
  points : number = 0;
  user : UserProfileDisplayDTO = new UserProfileDisplayDTO();
  currentUserId!: any;

  constructor(private service : UserService) { }

  ngOnInit(): void {
    this.currentUserId = localStorage.getItem("loggedUserId");
    this.service.getById2(this.currentUserId).subscribe(res => {
      if(res == null){
        this.user = new UserProfileDisplayDTO();
        this.visible = false;
        alert("User doesn't exist!");
        this.input_email = "";
      }
      else{
        this.user = res;
        console.log(this.user);
        
        console.log(this.user.phoneNumber);
        this.visible = true;
      }});
  }

  searchUser(){
    this.service.findByEmail(this.input_email).subscribe(res => {
      if(res == null){
        this.user = new UserProfileDisplayDTO();
        this.visible = false;
        alert("User doesn't exist!");
        this.input_email = "";
      }
      else{
        this.user = res;
        this.visible = true;
      }
    })
  }

  updateUser(){
    if(!this.isValidInput()){
      alert("Fill the fields correctly.")
    }else{
      this.service.update(this.user).subscribe(res => {
        this.user = res;
        alert("User profile updated successfully.");
      })
    }
    
  }

  public isValidInput(): boolean {

    if(this.user.firstName.length > 20) {
      alert('Name cannot be longer than 20 characters.');
      return false;
    }

    if(this.user.lastName.length > 30) {
      alert('Surname cannot be longer than 30 characters.');
      return false;
    }

    if(this.user.phoneNumber.length < 9) {
      alert('Phone number must be at least 9 digits long')
      return false;
    }
    var phoneNumberRegExp = /^(([0-9]{9,10}))$/;
    if(this.user.phoneNumber.length > 10) {
      alert("Phone number mustn't be longer than 10 digits.")
      return false;
    }
    if(!phoneNumberRegExp.test(String(this.user.phoneNumber))) {
      alert('Phone number format is not valid')
      return false;
    }
    var professionRegExp = /^(([A-Za-z]*))$/;
    if(this.user.profession == "") {
      alert('Profession field cannot be empty.');
      return false;
    }
    if(!professionRegExp.test(String(this.user.profession))) {
      alert('Profession format is not valid')
      return false;
    }
    if(this.user.professionInfo == "") {
      alert('ProfessionInfo field cannot be empty.');
      return false;
    }
    var genderRegExp = /^(([A-Za-z]*))$/;
    if(this.user.gender == "") {
      alert('Gender field cannot be empty.');
      return false;
    }
    if(!genderRegExp.test(String(this.user.gender))) {
      alert('Gender format is not valid')
      return false;
    }
    var nameSurnameRegExp = /^(([A-Za-z]*))$/;
    if(this.user.firstName == "") {
      alert('Name field cannot be empty.');
      return false;
    }
    if(!nameSurnameRegExp.test(String(this.user.firstName))) {
      alert('Name format is not valid')
      return false;
    }
    if(this.user.lastName == "") {
      alert('Surname field cannot be empty.');
      return false;
    }
    if(!nameSurnameRegExp.test(String(this.user.lastName))) {
      alert('Surname format is not valid')
      return false;
    }
    if(this.user.street == "") {
      alert('Street field cannot be empty.');
      return false;
    }
    if(this.user.number == "") {
      alert('Number field cannot be empty.');
      return false;
    }
    var cityCountryRegExp = /^(([A-Z][A-Za-z ]*))$/;
    if(this.user.city == "") {
      alert('City field cannot be empty.');
      return false;
    }
    if(!cityCountryRegExp.test(String(this.user.city))) {
      alert('City format is not valid')
      return false;
    }
    if(this.user.country == "") {
      alert('Country field cannot be empty.');
      return false;
    }
    if(!cityCountryRegExp.test(String(this.user.country))) {
      alert('Surname format is not valid')
      return false;
    }
    return true;
  }

}
