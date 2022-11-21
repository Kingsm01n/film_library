import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'

import { LoginComponent } from './login/login.component';
import { AppComponent } from "./app.component";
import {RegistrationComponent} from "./registration/registration.component";
import {AdminComponent} from "./admin/admin.component";

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'admin', component: AdminComponent },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [BrowserModule, RouterModule.forRoot(appRoutes), FormsModule, HttpClientModule],
  declarations: [AppComponent, LoginComponent, RegistrationComponent, AdminComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
