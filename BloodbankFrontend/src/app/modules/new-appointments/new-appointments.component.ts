import { Component, OnInit } from '@angular/core';
import { Appointment } from '../model/appointment.model';
import { AppointmentService } from '../services/appointment.service';
import { BloodBankService } from '../services/blood-bank-center.service';

@Component({
  selector: 'app-new-appointments',
  templateUrl: './new-appointments.component.html',
  styleUrls: ['./new-appointments.component.css']
})
export class NewAppointmentsComponent implements OnInit {

  date:Date = new Date();
  time: String = "07:00";
  appointments:Appointment[] = [];
  public displayedColumns = ['center', 'start', 'schedule'];
  public userId!: number;

  constructor(
    private appointmentService: AppointmentService,
    private centerService: BloodBankService) { }

  ngOnInit(): void {
    this.userId = parseInt(localStorage.getItem("loggedUserId")!)
  }


  show(){
    console.log("Show");
    var formatedDate = this.dateAsYYYYMMDDHHNNSS(this.date);
    console.log(formatedDate);
    this.appointmentService.getAavailableNewAppointments(formatedDate).subscribe(res=>{
      console.log("OHOHOHOHO");
      this.appointments = res;
      console.log(this.appointments);
    });
  }

  // https://stackoverflow.com/questions/40526102/how-do-you-format-a-date-time-in-typescript
  dateAsYYYYMMDDHHNNSS(date:any): string {
    return "2023-01-26T07:00:00";
    // return '2023'
    //           + '-' + this.leftpad(date.getMonth() + 1)
    //           + '-' + this.leftpad(date.getDate())
    //           + ' ' + this.time
    //           + ':' + this.leftpad(date.getSeconds());
  }
  leftpad(val:any, resultLength = 2, leftpadChar = '0'): string {
    return (String(leftpadChar).repeat(resultLength)
          + String(val)).slice(String(val).length);
  }

  schedule(appointment:Appointment){

  }
}
