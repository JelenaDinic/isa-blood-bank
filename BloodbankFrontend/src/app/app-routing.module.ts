import { AppointmentsComponent } from './modules/appointments/appointments.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchUsersComponent } from './modules/search-users/search-users.component';
import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';
import { BloodBankRegistrationComponent } from './modules/blood-bank-registration/blood-bank-registration.component';
import { StaffRegistrationComponent } from './modules/staff-registration/staff-registration.component';
import { UserRegistrationComponent } from './modules/user-registration/user-registration.component';
import { HomePageComponent } from './modules/home-page/home-page.component';
import { StaffProfileComponent } from './modules/staff-profile/staff-profile.component';
import { BloodDonorFormComponent } from './modules/blood-donor-form/blood-donor-form.component';
import { EditUserProfileComponent } from './modules/edit-user-profile/edit-user-profile.component';
import { AppointmentCalendarComponent } from './modules/appointment-calendar/appointment-calendar.component';


const routes: Routes = [
  { path: 'blood-bank-center', component: BloodBankCenterComponent },
  { path: 'search-users', component: SearchUsersComponent},
  { path: 'staff-profile', component: StaffProfileComponent},
  { path: 'register-blood-bank', component: BloodBankRegistrationComponent},
  { path: 'register-staff', component: StaffRegistrationComponent},
  { path: 'register-user', component: UserRegistrationComponent},
  { path: 'home-page', component: HomePageComponent},
  { path: 'blood-donor-form', component: BloodDonorFormComponent},
  { path: 'edit-user-profile', component: EditUserProfileComponent},
  { path: 'appointments', component: AppointmentsComponent},
  { path: 'appointments-calendar', component: AppointmentCalendarComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]  
})
export class AppRoutingModule { }
