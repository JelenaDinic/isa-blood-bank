import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';
import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './modules/services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { SearchUsersComponent } from './modules/search-users/search-users.component';
import { UserSearchFilterPipe } from './modules/pipes/user-search-filter.pipe';
import { BloodBankSortPipe } from './modules/pipes/blood-bank-sort.pipe';
import {MatButtonModule} from '@angular/material/button';
import { BloodBankRegistrationComponent } from './modules/blood-bank-registration/blood-bank-registration.component';
import { StaffRegistrationComponent } from './modules/staff-registration/staff-registration.component';
import { UserRegistrationComponent } from './modules/user-registration/user-registration.component';
import { HomePageComponent } from './modules/home-page/home-page.component';
import { StaffProfileComponent } from './modules/staff-profile/staff-profile.component';
import { MatIconModule } from '@angular/material/icon';
import { BloodDonorFormComponent } from './modules/blood-donor-form/blood-donor-form.component';
import { ToastrModule } from 'ngx-toastr';
import { EditUserProfileComponent } from './modules/edit-user-profile/edit-user-profile.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { BloodBankSearchPipe } from './modules/pipes/blood-bank-search.pipe';
import { NgxPaginationModule } from 'ngx-pagination';
import { AppointmentsComponent } from './modules/appointments/appointments.component';


@NgModule({
  declarations: [
    AppComponent,
    BloodBankCenterComponent,
    SearchUsersComponent,
    UserSearchFilterPipe,
    BloodBankSortPipe,
    BloodBankRegistrationComponent,
    StaffRegistrationComponent,
    UserRegistrationComponent,
    HomePageComponent,  
    StaffProfileComponent,
    BloodDonorFormComponent,
    EditUserProfileComponent,
    BloodBankSearchPipe,
    AppointmentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatCardModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatTableModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatGridListModule,
    ToastrModule.forRoot(),
    NgxPaginationModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
