import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StoreService } from './modules/services/store.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BloodbankFrontend';
  loggedUserJwt = localStorage.getItem('token');
  loggedUserRole = localStorage.getItem('loggedUserRole');

  constructor(private router: Router, public store: StoreService) {
  }

  onLogout() {
    localStorage.removeItem('token');
    this.store.setLoginStatus(0);
    // this.router.navigate(['/home']);
    console.log(localStorage.getItem('token'))
    location.reload();
  }
}
