import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {CreateFilmRequest} from "./create.film.request";

@Injectable()
export class AdminService {

  private url: string = "http://localhost:8080/api/v1/";
  constructor(private http: HttpClient) { }

  createFilm(createFilmRequest: CreateFilmRequest) {
    const filmUrl = this.url + "films";
    return this.http.post(filmUrl, createFilmRequest)
  }

}
