import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchUsersComponent } from './modules/search-users/search-users.component';
import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';
import { UpdateBloodBankCenterComponent } from './modules/update-blood-bank-center/update-blood-bank-center.component';
import { BloodBankRegistrationComponent } from './modules/blood-bank-registration/blood-bank-registration.component';
import { StaffRegistrationComponent } from './modules/staff-registration/staff-registration.component';
import { UserRegistrationComponent } from './modules/user-registration/user-registration.component';
import { HomePageComponent } from './modules/home-page/home-page.component';


const routes: Routes = [
  { path: 'blood-bank-center', component: BloodBankCenterComponent },
  { path: 'blood-bank-center/:id/update', component: UpdateBloodBankCenterComponent},
  { path: 'search-users', component: SearchUsersComponent},
  { path: 'register-blood-bank', component: BloodBankRegistrationComponent},
  { path: 'register-staff', component: StaffRegistrationComponent},
  { path: 'register-user', component: UserRegistrationComponent},
  { path: 'home-page', component: HomePageComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]  
})
export class AppRoutingModule { }
