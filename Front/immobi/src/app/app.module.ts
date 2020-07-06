import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { RegisterComponent } from './register/register.component';
import { ResultsComponent } from './results/results.component';
import { DetailsComponent } from './details/details.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './token-interceptor.service';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { AdminComponent } from './admin/admin.component';
import { AdminmenuComponent } from './adminmenu/adminmenu.component';
import { LocaladminComponent } from './localadmin/localadmin.component';
import { AdminlieuxComponent } from './adminlieux/adminlieux.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    MenuComponent,
    RegisterComponent,
    ResultsComponent,
    DetailsComponent,
    ConfirmationComponent,
    AdminComponent,
    AdminmenuComponent,
    LocaladminComponent,
    AdminlieuxComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    GoogleMapsModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
