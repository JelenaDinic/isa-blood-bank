import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchUsersComponent } from './modules/search-users/search-users.component';

const routes: Routes = [
  {path: 'search-users', component: SearchUsersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
