import { BloodBankCenter } from './../model/blood-bank-center.model';
import { BloodBankService } from './../services/blood-bank-center.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-blood-bank-center',
  templateUrl: './blood-bank-center.component.html',
  styleUrls: ['./blood-bank-center.component.css']
})
export class BloodBankCenterComponent implements OnInit {

  public bloodBankCenter: BloodBankCenter = new BloodBankCenter;
  public showUpdateBloodBankCenterComponent : boolean = false

  constructor(private bloodBankCenterService: BloodBankService, private router: Router) { }

  ngOnInit(): void {
        this.bloodBankCenterService.getBloodBankCenter(1).subscribe(res => {
          this.bloodBankCenter = res;
        })
  }
  public updateBloodBankCenter(id: number){
    this.router.navigate(['/blood-bank-center/' + id + '/update']);
    this.showUpdateBloodBankCenterComponent = true
  }

}
