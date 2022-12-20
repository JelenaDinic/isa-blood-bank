import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { PasswordChangeDTO } from '../dto/passwordChangeDTO';
import { SysadminService } from '../services/sysadmin.service';


@Component({
  selector: 'app-sysadmin-password-change',
  templateUrl: './sysadmin-password-change.component.html',
  styleUrls: ['./sysadmin-password-change.component.css']
})
export class SysadminPasswordChangeComponent {

  constructor(private sysAdminService: SysadminService, private toastr: ToastrService, private router: Router) {}

  passwordChangeDTO: PasswordChangeDTO = new PasswordChangeDTO();


  Submit(): void {

    if(this.passwordChangeDTO.newPassword !== this.passwordChangeDTO.confirmNewPassord) {
      this.toastr.error("Passwords do not match!");
      return;
    }

    let token = localStorage.getItem('token');
    let userId;
    if(token != null) {
      let decodedJWT = JSON.parse(window.atob(token.split(".")[1]))
      userId = decodedJWT.id.authority;
      this.passwordChangeDTO.userId = userId;
    }

    this.sysAdminService.changePassword(this.passwordChangeDTO).subscribe(res => {
      this.toastr.success("Password succesfully changed! Please log in again.")
      this.router.navigate(['/login-user']);
    }, (error) => {
      console.log(error)
      this.toastr.error(error.message);
    });

  }

}
