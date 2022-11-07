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

  public users!: UserDisplayDTO[];

  constructor(private userService: UserService) { }

  public getAllUsers(): void {
    this.userService.getAllUsers().subscribe(
      (response: UserDisplayDTO[]) => {
        this.users = response;
      }
     );
  }

  ngOnInit(): void {
    this.getAllUsers();
  }

}
