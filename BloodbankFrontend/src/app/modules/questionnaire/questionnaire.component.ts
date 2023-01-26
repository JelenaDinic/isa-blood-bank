import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NewAppointmentDTO } from '../dto/newAppointmentDTO';
import { BloodDonor } from '../model/blood-donor-form.model';
import { AppointmentService } from '../services/appointment.service';
import { BloodDonorService } from '../services/blood-donor.service';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css']
})
export class QuestionnaireComponent implements OnInit {

  bloodDonor : BloodDonor = new BloodDonor();
  centerId!: number;
  dateOfApp!: Date;
  appointment: NewAppointmentDTO = new NewAppointmentDTO();
  d!: any;

  constructor(private bloodDonorService: BloodDonorService, private appointmentService: AppointmentService, private router: Router) { 
    this.centerId = appointmentService.getCenterId();
    this.dateOfApp = appointmentService.getDateOfAppointment();
  

  }

  ngOnInit(): void {}
  
  bloodDonorRegister(){

    
    if (this.bloodDonor.hasSkinProblems.trim() == '' || this.bloodDonor.hasTattoo.trim() == '' || this.bloodDonor.hasUnacceptablePressure.trim() == '' || this.bloodDonor.isUnhealthy.trim() == ''
    || this.bloodDonor.isUnacceptableWeight.trim() == '' || this.bloodDonor.isUnderTreatment.trim() == ''|| this.bloodDonor.isRested.trim() == '' 
    || this.bloodDonor.isEpileptic.trim() == ''|| this.bloodDonor.hasDrankAlcohol.trim() == ''|| this.bloodDonor.hasAllergies.trim() == ''){
        alert('Please fill in all fields!');
        return;
     };
     if(this.bloodDonor.isPregnant.trim() == ''|| this.bloodDonor.isOnPeriod.trim() == ''){
      alert('Please fill in all fields!');
      return;
     };

    console.log(this.bloodDonor)
    this.bloodDonorService.create(this.bloodDonor).subscribe(data=>{
      
    });
    this.appointment.bloodBankCenterId = this.centerId;
    this.appointment.dateTime = this.dateOfApp;
    
    this.d = localStorage.getItem('loggedUserId');
    this.appointment.patientId = this.d;
    console.log(this.appointment);
    this.appointmentService.scheduleNewAppointment(this.appointment).subscribe(res=>{
      alert("Appointment successfully scheduled")
      this.router.navigate(['/home-page']);
    }, error=>alert("Appointment is not scheduled."));
  }
}
