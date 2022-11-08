import { BloodBankCenterComponent } from './modules/blood-bank-center/blood-bank-center.component';
import { UpdateBloodBankCenterComponent } from './modules/update-blood-bank-center/update-blood-bank-center.component';
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

@NgModule({
  declarations: [
    AppComponent,
    UpdateBloodBankCenterComponent,
    BloodBankCenterComponent,
    SearchUsersComponent,
    UserSearchFilterPipe
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
    BrowserAnimationsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
