import { Component, OnInit } from '@angular/core';
import { FreeExamination } from '../model/free-examination.model';
import { Staff } from '../model/staff.model';
import { FreeExaminationService } from '../services/free-examination.service';
import { StaffService } from '../services/staff.service';

@Component({
  selector: 'app-free-appointments',
  templateUrl: './free-appointments.component.html',
  styleUrls: ['./free-appointments.component.css']
})
export class FreeAppointmentsComponent implements OnInit{

  public freeExamination: FreeExamination = new FreeExamination;
  public staffList: Staff[] = []
  public date: Date = new Date;
  public time: string = ""
  public userId!: number;
  public centerId!: number;

  constructor(private staffService: StaffService, private freeExaminationService: FreeExaminationService) {
  }
  ngOnInit(): void {
    this.userId = parseInt(localStorage.getItem("loggedUserId")!)
    this.staffService.getById(this.userId).subscribe(res=> {
      this.centerId = res.bloodBankId;
      this.staffService.getStaffByCenterId(this.centerId).subscribe(res => {
        this.staffList = res;
      })
    })
    
  }
  create(date: any, time: any, freeExamination: FreeExamination): void {
    if(this.validate()) {
      freeExamination.dateTime = date + "T" + time + ":00"
      this.freeExaminationService.create(freeExamination).subscribe(res => {
        alert("You have created new examination successfully!")
      },(error) => {
        alert(error.message);
      });
    }

  }

  validate(): boolean {
    if(!this.date || !this.time || !this.freeExamination.staffId) {
      alert("Please fill in all the fields!")
      return false;
    } else if(this.freeExamination.duration > 60 || this.freeExamination.duration < 15) {
        alert("Duration can be only between 15 and 60 minutes")
        return false;
    }
    return true
  }
}
