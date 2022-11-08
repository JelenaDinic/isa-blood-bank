import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchUsersComponent } from './modules/search-users/search-users.component';
import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';
import { BloodBankRegistrationComponent } from './modules/blood-bank-registration/blood-bank-registration.component';
import { StaffRegistrationComponent } from './modules/staff-registration/staff-registration.component';


const routes: Routes = [
  { path: 'blood-bank-center', component: BloodBankCenterComponent },
  { path: 'search-users', component: SearchUsersComponent},
  { path: 'search-users', component: SearchUsersComponent},
  { path: 'register-blood-bank', component: BloodBankRegistrationComponent},
  { path: 'register-staff', component: StaffRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
