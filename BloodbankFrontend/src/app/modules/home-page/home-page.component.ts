import { Component, OnInit } from '@angular/core';
import { BloodBankCenterComponent } from '../blood-bank-center/blood-bank-center.component';
import { BloodBankCenter } from '../model/blood-bank-center.model';
import { BloodBankService } from '../services/blood-bank-center.service';
import { HomePageService } from '../services/home-page.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  public bloodBanks : BloodBankCenter[] = [];
  public allBloodBanks : BloodBankCenter[] = [];

  constructor(private homePageService: HomePageService) { }
  
  ngOnInit(): void {

    this.homePageService.getAll().subscribe(
      (response: BloodBankCenter[]) => {
        this.bloodBanks = response;
        this.allBloodBanks = this.bloodBanks;
      }
      );
  }

}
