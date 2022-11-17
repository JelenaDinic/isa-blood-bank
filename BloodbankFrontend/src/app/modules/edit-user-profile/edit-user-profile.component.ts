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

  constructor(private service : UserService) { }

  ngOnInit(): void {
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
    this.service.update(this.user).subscribe(res => {
      this.user = res;
      alert("User profile updated successfully.");
    })
  }


}
