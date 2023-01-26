import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ScheduleAppointmentDTO } from '../dto/scheduleAppointmentDTO';
import { AppointmentDisplay } from '../model/appointment-display.model';
import { Appointment } from '../model/appointment.model';
import { AppointmentService } from '../services/appointment.service';

@Component({
  selector: 'app-appointment-display',
  templateUrl: './appointment-display.component.html',
  styleUrls: ['./appointment-display.component.css']
})
export class AppointmentDisplayComponent {

  public dataSource = new MatTableDataSource<AppointmentDisplay>();
  public displayedColumns = ['dateTime', 'duration', 'status', 'bloodBankCenter', 'scheduleButton'];
  public appointments: AppointmentDisplay[] = [];
  public patientId!: number;
  clickedRows = new Set<Appointment>();
  row = new Appointment();
  loggedUserId = Number(localStorage.getItem('loggedUserId'))
  // public appointmentDTO!: ScheduleAppointmentDTO;

  constructor(
    private appointmentService: AppointmentService) { }

    ngOnInit(): void {
        this.appointmentService.getAllForScheduling().subscribe(res => {
          this.appointments = res;
          this.dataSource.data = this.appointments;
          this.appointments.forEach(appointment => {
            if(appointment.status == 'IN_FUTURE')
            {
              appointment.status = 'Scheduled';
            }
            if(appointment.status == 'CANCELLED')
            {
              appointment.status = 'Cancelled';
            }
            if(appointment.status == 'PENDING')
            {
              appointment.status = 'Pending';
            }
            if(appointment.status == 'FREE')
            {
              appointment.status = 'Free';
            }
            appointment.dateTime = appointment.dateTime.substring(0, 10) + ", " + appointment.dateTime.substring(11, 16);
          });
        })
      }
      

    schedule(appointmentId: any){
      console.log(appointmentId);
      console.log(Number(localStorage.getItem('loggedUserId')));

      let appointmentDTO = new ScheduleAppointmentDTO();

      appointmentDTO.customerId = Number(localStorage.getItem('loggedUserId'));

      appointmentDTO.id = appointmentId;

      this.appointmentService.scheduleAppointment(appointmentDTO).subscribe(data => {
        alert("Verify scheduling appointment on your email")
        },
        (error) => {alert(error.error)
        console.log(error)});
        
  
    }
}


