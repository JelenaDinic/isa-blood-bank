import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ScheduleAppointmentDTO } from '../dto/scheduleAppointmentDTO';
import { AppointmentDisplay } from '../model/appointment-display.model';
import { Appointment } from '../model/appointment.model';
import { AppointmentService } from '../services/appointment.service';

@Component({
  selector: 'app-scheduled-appointments',
  templateUrl: './scheduled-appointments.component.html',
  styleUrls: ['./scheduled-appointments.component.css']
})
export class ScheduledAppointmentsComponent {

  public dataSource = new MatTableDataSource<AppointmentDisplay>();
  public displayedColumns = ['dateTime', 'duration', 'status', 'bloodBankCenter', 'cancelButton'];
  public appointments: AppointmentDisplay[] = [];
  public patientId!: number;
  clickedRows = new Set<Appointment>();
  row = new Appointment();
  loggedUserId = Number(localStorage.getItem('loggedUserId'))
  // public appointmentDTO!: ScheduleAppointmentDTO;

  constructor(
    private appointmentService: AppointmentService) { }

    ngOnInit(): void {
        this.appointmentService.getAllScheduled(this.loggedUserId).subscribe(res => {
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
      
    cancel(appointmentId: any){
      console.log(appointmentId);
      console.log(Number(localStorage.getItem('loggedUserId')));

      let appointmentDTO = new ScheduleAppointmentDTO();

      appointmentDTO.customerId = Number(localStorage.getItem('loggedUserId'));
      appointmentDTO.id = appointmentId;

      
      this.appointmentService.cancelAppointment(appointmentDTO).subscribe(res => {
        alert("Appointment successfully cancelled")
        })
    }

}
