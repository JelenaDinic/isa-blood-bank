import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';
import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './modules/services/user.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { AppointmentCalendarComponent } from './modules/appointment-calendar/appointment-calendar.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { CommonModule } from '@angular/common';
import { FlatpickrModule } from 'angularx-flatpickr';
import { LoginUserComponent } from './modules/login-user/login-user.component';
import { TokenInterceptor } from './modules/interceptor/TokenInterceptor';
import { ForbiddenComponent } from './modules/forbidden/forbidden.component';
import { BsDatepickerModule, BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
//import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';


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
    AppointmentsComponent,
    AppointmentCalendarComponent,
    LoginUserComponent,
    ForbiddenComponent
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
    NgxPaginationModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  providers: [
    UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
],
  bootstrap: [AppComponent]
})
export class AppModule { }
