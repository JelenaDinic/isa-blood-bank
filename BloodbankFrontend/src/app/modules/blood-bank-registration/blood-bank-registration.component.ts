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
    if (this.bloodBankCenter.name == '' || this.bloodBankCenter.address == ''
     || this.bloodBankCenter.description == '' || this.bloodBankCenter.averageGrade == null) {
        alert('Please fill in all fields!');
        return;
     }
    this.bloodBankService.create(this.bloodBankCenter).subscribe(res => {
      this.router.navigate(['/']);
    });
  }

}