import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { LoginUser } from '../model/login-user.model';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {

  public loginUser : LoginUser = new LoginUser();
  public token : string = "";
  public role : string = "";
  constructor(private toastr: ToastrService, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  Login(){
    this.loginService.login(this.loginUser).subscribe((response: any) => {
      this.token = response;
      localStorage.setItem('token',this.token);
      console.log(this.token)
      let decodedJWT = JSON.parse(window.atob(this.token.split('.')[1]));
      this.role = decodedJWT.role.authority;
      localStorage.setItem('loggedUserRole', this.role);
      console.log(this.role);
      this.toastr.success('Successfully logged in');
      if(this.role == 'ROLE_STAFF')
      window.location.href = '/staff-profile';

    },
    (error) => {
      console.log(error.message);
    }
   );
  }
}