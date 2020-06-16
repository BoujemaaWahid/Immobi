import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';


const routes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full', data: { animation: 'isHome'}},
  {path: 'login', component: LoginComponent, data: { animation: 'isLogin'}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
