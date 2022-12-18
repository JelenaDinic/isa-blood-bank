import { Equipment } from './../model/equipment.model';
import { AppointmentReport } from './../model/appointment-report.model';
import { UserService } from './../services/user.service';
import { BloodDonorService } from './../services/blood-donor.service';
import { Appointment } from './../model/appointment.model';
import { AppointmentService } from './../services/appointment.service';
import { AppointmentReportService } from './../services/appointment-report.service';
import { EquipmentService } from './../services/equipment.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  public appointments: Appointment[] = []
  public appointmentReport: AppointmentReport = new AppointmentReport;
  public displayedColumns = ['user', 'date', 'duration', 'status', 'update'];
  public isAllowed: boolean | undefined;
  public isPressed: boolean = false;
  public userFirstName: string = "";
  public userLastName: string = "";
  public startedAppointmentId: number = 0;
  public equipmentArr: Equipment[] = [];

  constructor(
    private appointmentService: AppointmentService,
    private bloodDonorService: BloodDonorService,
    private userService: UserService,
    private appointmentReportService: AppointmentReportService,
    private equipmentService: EquipmentService) { }

  ngOnInit(): void {
    this.isPressed = false;
    this.bloodDonorService.checkIfAllowed(2010).subscribe(res => {
      if (res != null)
        this.isAllowed = res;
      this.appointmentService.findByUserId(2010).subscribe(res => {
        this.appointments = res;
        this.startedAppointmentId = res[0].id
      })
    })
  }
  start(appointment: Appointment): void {
    this.isPressed = true;
    this.userService.getById(appointment.userId).subscribe(res => {
      this.userFirstName = res.firstName;
      this.userLastName = res.lastName;

    })
  }
  addPenalty(appointment: Appointment): void {
    this.appointmentService.addPenalty(appointment).subscribe(res => {
      window.location.reload();
    })
  }
  saveReport(appointmentReport: AppointmentReport): void {
    appointmentReport.appointmentId = this.startedAppointmentId;
    this.appointmentReportService.create(appointmentReport).subscribe(res => {
      this.equipmentService.updateEquipment(this.equipmentArr).subscribe(res => {
        alert("You have saved this report successfully!")
        window.location.reload();
      })

    });
  }
  newEquipment(): void {
    this.equipmentArr.push({ type: "", quantity: 0 });
  }

}
