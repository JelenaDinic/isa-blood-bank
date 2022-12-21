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
import { LoginUserComponent } from './modules/login-user/login-user.component';
import { Guard } from './modules/guard/guard';
import { SysadminRegistrationComponent } from './modules/sysadmin-registration/sysadmin-registration.component';
import { ForbiddenComponent } from './modules/forbidden/forbidden.component';
import { SysadminPasswordChangeComponent } from './modules/sysadmin-password-change/sysadmin-password-change.component';
import { ComplaintsDisplayComponent } from './modules/complaints-display/complaints-display.component';


const routes: Routes = [
  { path: 'blood-bank-center', component: BloodBankCenterComponent, canActivate:[Guard], data:{ permittedRoles:["ROLE_USER"]}},
  { path: 'search-users', component: SearchUsersComponent, canActivate:[Guard], data:{ permittedRoles:["ROLE_STAFF", "ROLE_SYSTEMADMIN"]}},
  { path: 'staff-profile', component: StaffProfileComponent, canActivate:[Guard], data:{ permittedRoles:["ROLE_STAFF"]}},
  { path: 'register-blood-bank', component: BloodBankRegistrationComponent, canActivate:[Guard], data:{ permittedRoles:["ROLE_STAFF"]}},
  { path: 'register-staff', component: StaffRegistrationComponent, canActivate:[Guard], data:{ permittedRoles:["ROLE_STAFF"]}},
  { path: 'register-user', component: UserRegistrationComponent},
  { path: 'home-page', component: HomePageComponent},
  { path: 'blood-donor-form', component: BloodDonorFormComponent},
  { path: 'edit-user-profile', component: EditUserProfileComponent, canActivate:[Guard]},
  { path: 'appointments', component: AppointmentsComponent},
  { path: 'login-user', component: LoginUserComponent},
  { path: 'appointments-calendar', component: AppointmentCalendarComponent, canActivate:[Guard], data:{ permittedRoles:["ROLE_STAFF"]}},
  { path: 'forbidden', component: ForbiddenComponent},
  { path: 'register-sysadmin', component: SysadminRegistrationComponent, canActivate:[Guard], data:{ permittedRoles:["ROLE_SYSTEMADMIN"]}},
  { path: 'password-change', component: SysadminPasswordChangeComponent},
  { path: 'complaints-display', component: ComplaintsDisplayComponent, canActivate:[Guard], data:{ permittedRoles:["ROLE_SYSTEMADMIN"]}},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]  
})
export class AppRoutingModule { }
