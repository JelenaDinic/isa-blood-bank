import { Component, OnInit } from '@angular/core';
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
  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  Login(){
    this.loginService.login(this.loginUser).subscribe((response: string) => {
      this.token = response;
      localStorage.setItem('token',this.token);
      console.log(this.token)
    },
    (error) => {
      console.log(error.message);
    }
   );
  }
}