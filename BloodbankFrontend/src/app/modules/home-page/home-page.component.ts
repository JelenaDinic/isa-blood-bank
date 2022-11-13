import { Component, OnInit } from '@angular/core';
import { BloodBankCenterComponent } from '../blood-bank-center/blood-bank-center.component';
import { BloodBankDisplayDTO } from '../dto/bloodBankDisplayDTO';
import { BloodBankCenter } from '../model/blood-bank-center.model';
import { BloodBankService } from '../services/blood-bank-center.service';
import { HomePageService } from '../services/home-page.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  public bloodBanks : BloodBankDisplayDTO[] = [];
  public allBloodBanks : BloodBankDisplayDTO[] = [];
  SortByParam = '';
  sortDirection = 'asc';

  constructor(private homePageService: HomePageService) { }
  
  ngOnInit(): void {

    this.homePageService.getAll().subscribe(
      (response: BloodBankDisplayDTO[]) => {
        this.bloodBanks = response;
        this.allBloodBanks = this.bloodBanks;
        
        console.log(this.allBloodBanks);
      }
      );
  }

  onSortDirection(){
    if (this.sortDirection === 'desc'){
      this.sortDirection = 'asc';
    }
    else
      this.sortDirection = 'desc';
  }

}
