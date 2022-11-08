import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BloodBankCenter } from '../model/blood-bank-center.model';
import { BloodBankService } from '../services/blood-bank-center.service';

@Component({
  selector: 'app-update-blood-bank-center',
  templateUrl: './update-blood-bank-center.component.html',
  styleUrls: ['./update-blood-bank-center.component.css']
})
export class UpdateBloodBankCenterComponent implements OnInit {

  public bloodBankCenter: BloodBankCenter | undefined = undefined;
  constructor(private bloodBankCenterService: BloodBankService, private router: Router) { }

  ngOnInit(): void {
    this.bloodBankCenterService.getBloodBankCenter(1).subscribe(res => {
      this.bloodBankCenter = res;
    })
  }
  public update(bloodBankCenter: BloodBankCenter): void {
    if (!this.isValidInput())
    {
      alert("Field cannot be empty!")
        this.router.navigate(['/blood-bank-center']);
    }
    else
    {
      this.bloodBankCenterService.update(bloodBankCenter).subscribe(res => {
        this.router.navigate(['/blood-bank-center']);
      });
    }
  }

  private isValidInput(): boolean {
    return this.bloodBankCenter?.name != '' && this.bloodBankCenter?.description != '';
  }

}
