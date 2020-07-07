import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ResultsComponent } from './results/results.component';
import { DetailsComponent } from './details/details.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { AdminComponent } from './admin/admin.component';
import { LocaladminComponent } from './localadmin/localadmin.component';
import { AdminlieuxComponent } from './adminlieux/adminlieux.component';
import { VillesadminComponent } from './villesadmin/villesadmin.component';
import { RegionsadminComponent } from './regionsadmin/regionsadmin.component';

const routes: Routes = [
  {path: '', component: HomeComponent, data: { animation: 'isHome'}},
  {path: 'login', component: LoginComponent, data: { animation: 'isLogin'}},
  {path: 'register', component: RegisterComponent, data: {animation: 'isRegister'}},
  {path: 'recherche', component: ResultsComponent, data: {animation: 'isResults'}},
  {path: 'details', component: DetailsComponent, data: {animation: 'isDetails'}},
  {path: 'validation', component: ConfirmationComponent},
  {path: 'admin', component: AdminComponent, children: [
    {path: 'locales', component: LocaladminComponent},
    {path: 'adresses', component: AdminlieuxComponent},
    {path: 'villes', component: VillesadminComponent},
    {path: 'regions', component: RegionsadminComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
