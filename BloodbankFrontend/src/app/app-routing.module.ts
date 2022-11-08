import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';
import { UpdateBloodBankCenterComponent } from './modules/update-blood-bank-center/update-blood-bank-center.component';

const routes: Routes = [
  { path: 'blood-bank-center', component: BloodBankCenterComponent },
  { path: 'blood-bank-center/:id/update', component: UpdateBloodBankCenterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
