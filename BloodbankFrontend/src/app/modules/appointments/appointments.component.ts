import { BloodDonorService } from './../services/blood-donor.service';
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

  constructor(private appointmentService: AppointmentService, private bloodDonorService: BloodDonorService) { }

  ngOnInit(): void {
    this.bloodDonorService.checkIfAllowed(2010).subscribe(res => {
      if(res != null)
        this.isAllowed = res;
      this.appointmentService.findByUserId(2010).subscribe(res => {
        this.appointments = res;
      })
    })
    
  }

}
