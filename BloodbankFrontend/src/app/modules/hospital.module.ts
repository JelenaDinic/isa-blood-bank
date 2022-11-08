import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { MaterialModule } from 'src/app/material/material.module';
import { BloodBankCenterComponent } from './blood-bank-center/blood-bank-center.component';
import { SearchUsersComponent } from './search-users/search-users.component';
import { UserSearchFilterPipe } from './pipes/user-search-filter.pipe';

const routes: Routes = [
    { path: 'blood-bank-center/:id', component: BloodBankCenterComponent },
    {path: 'search-users', component: SearchUsersComponent}
  ];
  @NgModule({
    declarations: [
      BloodBankCenterComponent,
      SearchUsersComponent,
      UserSearchFilterPipe
    ],
    imports: [
      CommonModule,
      MaterialModule,
      FormsModule,
      ReactiveFormsModule,
      RouterModule.forChild(routes)
    ],
    exports: [RouterModule],
  })
  export class HospitalModule {}