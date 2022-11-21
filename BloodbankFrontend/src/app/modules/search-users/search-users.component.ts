import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
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
  page: number = 0;
  cardsCount: number = 2;
  count: number = 0;
  searchTerm = '';

  constructor(private userService: UserService) { }

  public getUsers(searchText: string = ''): void {
    this.userService.search(searchText).subscribe(
      (response: UserDisplayDTO[]) => {
        this.users = response;
        this.count = this.users.length;
      },
      (error) => {
        console.log(error.message);
      }
     );
  }

  ngOnInit(): void {
    this.getUsers();
  }

  search(value: string): void {
    this.getUsers(value);
  }

  onTableDataChange(event: any) {
    this.page = event;
    this.getUsers(this.searchTerm);
  }

}
