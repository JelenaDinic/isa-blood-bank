import { Component, OnInit } from '@angular/core';
import { BloodDonor } from '../model/blood-donor.model';
import { BloodDonorService } from '../services/blood-donor.service';

@Component({
  selector: 'app-blood-donor-form',
  templateUrl: './blood-donor-form.component.html',
  styleUrls: ['./blood-donor-form.component.css']
})
export class BloodDonorFormComponent implements OnInit {

  bloodDonor : BloodDonor = new BloodDonor();

  constructor(private bloodDonorService: BloodDonorService) { }

  ngOnInit(): void {}
  
  bloodDonorRegister(){
    console.log(this.bloodDonor)
    this.bloodDonorService.create(this.bloodDonor).subscribe(data=>{
      alert("User successfully registered")
    },error=>alert("User is not registered"));
  }
}
