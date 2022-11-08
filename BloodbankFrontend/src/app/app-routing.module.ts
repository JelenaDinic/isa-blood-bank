import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchUsersComponent } from './modules/search-users/search-users.component';
import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';
import { UpdateBloodBankCenterComponent } from './modules/update-blood-bank-center/update-blood-bank-center.component';
import { BloodBankRegistrationComponent } from './modules/blood-bank-registration/blood-bank-registration.component';


const routes: Routes = [
  { path: 'blood-bank-center', component: BloodBankCenterComponent },
  { path: 'blood-bank-center/:id/update', component: UpdateBloodBankCenterComponent},
  { path: 'search-users', component: SearchUsersComponent},
  { path: 'register-blood-bank', component: BloodBankRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
