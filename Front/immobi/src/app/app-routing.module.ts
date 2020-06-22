import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ResultsComponent } from './results/results.component';
import { DetailsComponent } from './details/details.component';


const routes: Routes = [
  {path: '', component: HomeComponent, data: { animation: 'isHome'}},
  {path: 'login', component: LoginComponent, data: { animation: 'isLogin'}},
  {path: 'register', component: RegisterComponent, data: {animation: 'isRegister'}},
  {path: 'recherche', component: ResultsComponent, data: {animation: 'isResults'}},
  {path: 'details', component: DetailsComponent, data: {animation: 'isDetails'}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
