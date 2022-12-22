import { Component, OnInit } from '@angular/core';
import { BloodDonor } from '../model/blood-donor-form.model';
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

    
    if (this.bloodDonor.hasSkinProblems.trim() == '' || this.bloodDonor.hasTattoo.trim() == '' || this.bloodDonor.hasUnacceptablePressure.trim() == '' || this.bloodDonor.isUnhealthy.trim() == ''
    || this.bloodDonor.isUnacceptableWeight.trim() == '' || this.bloodDonor.isUnderTreatment.trim() == ''|| this.bloodDonor.isRested.trim() == '' 
    || this.bloodDonor.isEpileptic.trim() == ''|| this.bloodDonor.hasDrankAlcohol.trim() == ''|| this.bloodDonor.hasAllergies.trim() == ''){
        alert('Please fill in all fields!');
        return;
     };
     if(this.bloodDonor.isPregnant.trim() == ''|| this.bloodDonor.isOnPeriod.trim() == ''){
      alert('Please fill in all fields!');
      return;
     };

    console.log(this.bloodDonor)
    this.bloodDonorService.create(this.bloodDonor).subscribe(data=>{
      alert("User successfully registered")
    },error=>alert("User is not registered"));
  }
}
