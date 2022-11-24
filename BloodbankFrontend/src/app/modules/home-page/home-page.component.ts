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
  SearchName : string = '';
  SearchCity : string = '';
  FilterByRating : number = 0;
  page: number = 0;
  cardsCount: number = 2;
  count: number = 0;
  public temp_data: BloodBankDisplayDTO[] = [];

  constructor(private homePageService: HomePageService) { }
  
  ngOnInit(): void {
    this.getBloodBanks(this.SearchName, this.SearchCity)
  }

  onSortDirection(){
    if (this.sortDirection === 'desc'){
      this.sortDirection = 'asc';
    }
    else
      this.sortDirection = 'desc';
  }

  onChange(){
    this.getBloodBanks(this.SearchName, this.SearchCity, this.FilterByRating)
  }

  onTableDataChange(event: any) {
    this.page = event;
    this.getBloodBanks(this.SearchName, this.SearchCity);
  }
  
  public getBloodBanks(searchName: string = '', searchCity: string = '', filterbyRating: number = 0): void {
    this.homePageService.searchBloodBanks(searchName, searchCity, filterbyRating).subscribe(
      (response: BloodBankDisplayDTO[]) => {
        this.bloodBanks = response;
        this.allBloodBanks = this.bloodBanks;
        this.temp_data = this.allBloodBanks;
        
        console.log(this.allBloodBanks);
        this.count = this.allBloodBanks.length;
      },
      (error) => {
        console.log(error.message);
      }
     );
  }
  searchBloodBanks(name: string, city: string, rating: number){
    this.getBloodBanks(name, city, rating);
  }
}
