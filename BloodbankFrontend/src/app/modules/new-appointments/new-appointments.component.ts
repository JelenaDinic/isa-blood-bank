import { Component, OnInit, ViewChild } from '@angular/core';
import { Appointment } from '../model/appointment.model';
import { AppointmentService } from '../services/appointment.service';
import { BloodBankService } from '../services/blood-bank-center.service';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { Router } from '@angular/router';
import { AppointmentDisplay } from '../model/appointment-display.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';


@Component({
  selector: 'app-new-appointments',
  templateUrl: './new-appointments.component.html',
  styleUrls: ['./new-appointments.component.css']
})
export class NewAppointmentsComponent implements OnInit {

  date:Date = new Date();
  time: String = "07:00";
  appointments:AppointmentDisplay[] = [];
  public displayedColumns = ['center', 'city', 'grade', 'start', 'schedule'];
  public userId!: number;
  data = new MatTableDataSource<AppointmentDisplay>();

  @ViewChild(MatSort) set sort(sort: MatSort){
    this.data.sort = sort;
}
  

  constructor(
    private appointmentService: AppointmentService,
    private centerService: BloodBankService,
    private router: Router) { }

  ngOnInit(): void {
    this.userId = parseInt(localStorage.getItem("loggedUserId")!)
  }


  show(){
    console.log("Show");
    var formatedDate = this.dateAsYYYYMMDDHHNNSS(this.date);
    console.log(formatedDate);
    this.appointmentService.getAavailableNewAppointments(formatedDate).subscribe(res=>{
      this.appointments = res;
      this.data.data = this.appointments;
      this.data = new MatTableDataSource<AppointmentDisplay>(res);
      console.log(this.appointments);
    });
  }

  // https://stackoverflow.com/questions/40526102/how-do-you-format-a-date-time-in-typescript
  dateAsYYYYMMDDHHNNSS(date:any): string {
    //return "2023-01-26T07:00:00";
    var date2 = new Date(this.date);
    return date2.getFullYear()
              + '-' + this.leftpad(date2.getMonth() + 1)
              + '-' + this.leftpad(date2.getDate())
              + 'T' + this.time
              + ':' + "00";
  }
  leftpad(val:any, resultLength = 2, leftpadChar = '0'): string {
    return (String(leftpadChar).repeat(resultLength)
          + String(val)).slice(String(val).length);
  }

  schedule(appointment:AppointmentDisplay){
    this.appointmentService.setCenterId(appointment.bloodBankCenter.id);
    this.appointmentService.setDateOfAppointment(appointment.dateTime);
    
    this.router.navigate(["/questionnaire"]);
    
  }
}
