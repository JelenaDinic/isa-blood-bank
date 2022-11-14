import { UserDisplayDTO } from './../dto/userDisplayDTO';
import { BloodBankCenter } from './../model/blood-bank-center.model';
import { BloodBankService } from './../services/blood-bank-center.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StaffService } from '../services/staff.service';

@Component({
  selector: 'app-blood-bank-center',
  templateUrl: './blood-bank-center.component.html',
  styleUrls: ['./blood-bank-center.component.css']
})
export class BloodBankCenterComponent implements OnInit {

  public bloodBankCenter: BloodBankCenter = new BloodBankCenter;
  public staffList: any;

  constructor(private bloodBankCenterService: BloodBankService, private staffService: StaffService, private router: Router) { }

  

  ngOnInit(): void {
        this.bloodBankCenterService.getBloodBankCenter(11).subscribe(res => {
          this.bloodBankCenter = res;
          console.log(this.bloodBankCenter)
          this.staffService.getStaffByCenterId(this.bloodBankCenter.id).subscribe(res => {
            this.staffList = res;
          })
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
        alert("Updated successfully!")
      });
    }
  }

  private isValidInput(): boolean {
    return this.bloodBankCenter?.name != '' && this.bloodBankCenter?.description != ''
    && this.bloodBankCenter?.city != '' && this.bloodBankCenter?.country != ''
    && this.bloodBankCenter?.number != '' && this.bloodBankCenter?.street != '';
  }


}
