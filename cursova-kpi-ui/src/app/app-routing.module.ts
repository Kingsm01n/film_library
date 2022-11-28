import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {Routes, RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'

import {LoginComponent} from './login/login.component';
import {AppComponent} from "./app.component";
import {RegistrationComponent} from "./registration/registration.component";
import {AdminCreateFilmComponent} from "./admin/create-film/admin-create-film.component";
import {AdminFilmsComponent} from "./admin/films/admin-films.component";
import {AdminGenresComponent} from "./admin/genres/admin-genres.component";
import {AdminActorsComponent} from "./admin/actors/admin-actors.component";
import {AdminEditFilmComponent} from "./admin/edit-film/admin-edit-film.component";

const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'admin/createFilm', component: AdminCreateFilmComponent},
  {path: 'admin/films', component: AdminFilmsComponent},
  {path: 'admin/films/edit', component: AdminEditFilmComponent},
  {path: 'admin/genres', component: AdminGenresComponent},
  {path: 'admin/actors', component: AdminActorsComponent},
  {path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [BrowserModule, RouterModule.forRoot(appRoutes), FormsModule, HttpClientModule],
  declarations: [AppComponent, LoginComponent, RegistrationComponent, AdminCreateFilmComponent, AdminFilmsComponent, AdminGenresComponent, AdminActorsComponent, AdminEditFilmComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
