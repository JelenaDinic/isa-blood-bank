import { Component, OnInit } from '@angular/core';
import { BloodBankCenter } from '../model/blood-bank-center.model';
import { BloodBankService } from '../services/blood-bank-center.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-blood-bank-registration',
  templateUrl: './blood-bank-registration.component.html',
  styleUrls: ['./blood-bank-registration.component.css']
})
export class BloodBankRegistrationComponent implements OnInit {

  public bloodBankCenter : BloodBankCenter = new BloodBankCenter();

  constructor(private bloodBankService: BloodBankService, private router: Router) { }

  ngOnInit(): void {
  }

  public registerBloodBank() {

    if(this.isInputValid()) {
      this.bloodBankService.create(this.bloodBankCenter).subscribe(res => {
        this.router.navigate(['/']);
      });
    }
    
  }

  public isInputValid(): boolean {
    if (this.bloodBankCenter.name == '' || this.bloodBankCenter.description == '' || this.bloodBankCenter.averageGrade == null || this.bloodBankCenter.street == ''
    || this.bloodBankCenter.number == '' || this.bloodBankCenter.city == '' || this.bloodBankCenter.country == '') {
      console.log(this.bloodBankCenter)
      alert('Please fill in all fields!');
      return false;
    }


    return true;
  }

}
