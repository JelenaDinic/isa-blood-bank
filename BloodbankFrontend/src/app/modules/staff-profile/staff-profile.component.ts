import { Component, OnInit } from '@angular/core';
import { Staff } from '../model/staff.model';
import { StaffService } from '../services/staff.service';

@Component({
  selector: 'app-staff-profile',
  templateUrl: './staff-profile.component.html',
  styleUrls: ['./staff-profile.component.css']
})
export class StaffProfileComponent implements OnInit {

  public staff: Staff = new Staff;
  constructor(private staffService: StaffService) { }

  ngOnInit(): void {
    this.staffService.getById(13).subscribe(res => {
      this.staff = res;
    })
  }

  public update(staff: Staff): void {
    
      this.staffService.update(staff).subscribe(res => {
        //this.router.navigate(['/blood-bank-center']);
      });
  }

}
