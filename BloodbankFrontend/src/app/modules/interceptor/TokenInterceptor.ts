import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private router: Router) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      if(localStorage.getItem('token') != null) {
          const clonedRequest = req.clone({
              headers : req.headers.append('Authorization', 'Bearer ' + localStorage.getItem('token'))
          });

          return next.handle(clonedRequest).pipe(
              tap(
                  succ => {},
                  err => {
                      if(err.status == 401) {
                          localStorage.removeItem('token');
                          this.router.navigate(['/login-user']); 
                      }
                      else if(err.status == 403) {
                          this.router.navigate(['/forbidden']);
                      }
                  }
              )
          )
      }

      else {
          return next.handle(req.clone());
      }
  }
}