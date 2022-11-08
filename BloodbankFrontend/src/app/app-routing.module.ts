import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchUsersComponent } from './modules/search-users/search-users.component';
import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';


const routes: Routes = [
  { path: 'blood-bank-center', component: BloodBankCenterComponent },
  { path: 'search-users', component: SearchUsersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
