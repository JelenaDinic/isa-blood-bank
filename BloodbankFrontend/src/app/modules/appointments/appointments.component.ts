import { Appointment } from './../model/appointment.model';
import { AppointmentService } from './../services/appointment.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  public appointments: Appointment[] = []
  public displayedColumns = ['user', 'date', 'duration', 'status', 'update'];
  public isAllowed: boolean | undefined;

  constructor(private appointmentService: AppointmentService) { }

  ngOnInit(): void {
    this.appointmentService.findByUserId(2010).subscribe(res => {
      this.appointments = res;
      if(this.appointments[0].status === "Not allowed")
        this.isAllowed = false;
      else
        this.isAllowed = true
      console.log(this.appointments)
    })
  }

}
