import { UserDisplayDTO } from './../dto/userDisplayDTO';
import { BloodBankCenter } from './../model/blood-bank-center.model';
import { BloodBankService } from './../services/blood-bank-center.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StaffService } from '../services/staff.service';
import { BloodSupplyService } from '../services/blood-supply.service';
import { BloodSupplyDTO } from '../dto/bloodSupplyDTO';
import { MatTableDataSource } from '@angular/material/table';
import { Staff } from '../model/staff.model';

@Component({
  selector: 'app-blood-bank-center',
  templateUrl: './blood-bank-center.component.html',
  styleUrls: ['./blood-bank-center.component.css']
})
export class BloodBankCenterComponent implements OnInit {

  public bloodBankCenter: BloodBankCenter = new BloodBankCenter;
  public staffList: any;
  public displayedColumns = ['bloodType', 'amount'];
  public userId!: number;
  public staff: Staff = new Staff;
  public dataSource = new MatTableDataSource<BloodSupplyDTO>();
  constructor(private bloodBankCenterService: BloodBankService, private staffService: StaffService, private bloodSupplyService: BloodSupplyService, private router: Router) { }

  

  ngOnInit(): void {
    this.userId = parseInt(localStorage.getItem("loggedUserId")!)
    this.staffService.getById(this.userId).subscribe(res => {
      this.staff = res
      this.bloodBankCenterService.getBloodBankCenter(this.staff.bloodBankId).subscribe(res => {
        this.bloodBankCenter = res;
        this.staffService.getStaffByCenterId(this.bloodBankCenter.id).subscribe(res => {
          this.staffList = res;
        })
        this.bloodSupplyService.getByCenterId(this.bloodBankCenter.id).subscribe(res => {
          this.dataSource = res;
        })
      })
    });
        
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
