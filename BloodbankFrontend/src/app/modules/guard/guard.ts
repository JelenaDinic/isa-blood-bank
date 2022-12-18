import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class Guard implements CanActivate {

  constructor(private router: Router, private loginService: LoginService) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if(localStorage.getItem('token') != null){
      let roles = route.data['permittedRoles'] as Array<string>;
      if(roles) { 
        if(this.loginService.roleMatch(roles)) return true;
        else {
          this.router.navigate(['/forbidden']);
          return false;
        }
      }
      return true;
    }
      

    else {
      this.router.navigate(['/login-user']);
      return false;
    }

  }
  
}
