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
  loggedUserId = localStorage.getItem('loggedUserId')

  constructor(private router: Router, public store: StoreService) {
  }

  onLogout() {
    localStorage.removeItem('token');
    localStorage.removeItem('loggedUserRole')
    localStorage.removeItem('loggedUserId')
    this.store.setLoginStatus(0);
    this.router.navigate(['/login-user']);
    location.reload()
  }
}
