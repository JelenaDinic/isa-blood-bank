import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDisplayDTO } from '../dto/userDisplayDTO';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})
export class SearchUsersComponent implements OnInit {

  public users: UserDisplayDTO[] = [];
  public allUsers: UserDisplayDTO[] = [];
  page: number = 1;
  cardsCount: number = 2;
  count: number = 0;
  searchTerm = '';
  loggedUserRole = localStorage.getItem('loggedUserRole');

  constructor(private router: Router, private userService: UserService) { }

  // public getUsers(searchText: string = ''): void {
  //   this.userService.search(searchText).subscribe(
  //     (response: UserDisplayDTO[]) => {
  //       this.users = response;
  //       this.count = this.users.length;
  //     },
  //     (error) => {
  //       console.log(error.message);
  //     }
  //    );
  // }

  public getUsers(page: number = 0, size: number = 2,searchTerm : string = ''): void{
    this.userService.search(page, size, searchTerm).subscribe(
      (res) => {
        this.users = res;
        this.getNumberOfUsers(this.searchTerm);
        console.log(res);
        
      }
    );
  }

  public getNumberOfUsers(searchTerm: string = ''): void{
    this.userService.getResultCount(searchTerm).subscribe(
      (res) => {
        this.count = res;
        if(this.page -1 > (this.count/this.cardsCount)){
          this.onTableDataChange(1);
        }
      }
    );
  }

  ngOnInit(): void {
    this.getUsers();
  }

  search(value: string): void {
    this.getUsers(this.page -1, this.cardsCount, this.searchTerm);
  }

  onTableDataChange(event: any) {
    this.page = event;
    this.getUsers(this.page-1, this.cardsCount , this.searchTerm);
  }

  showAppointments(id: number): void {
    localStorage.setItem('selectedUser', id.toString());
    this.router.navigate(['/appointments'])
  }

}
