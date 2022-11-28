import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CreateGenreRequest} from "./create-genre.request";

@Injectable()
export class AdminGenresService {

  private url: string = "http://localhost:8080/api/v1/";

  constructor(private http: HttpClient) {
  }

  getGenres() {
    const genreUrl = this.url + "genres";

    const headers = {
      "Content-Type": "application/json" || '',
      "Accept": "application/json" || '',
      "Access-Control-Allow-Headers": "Content-Type" || '',
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.get(genreUrl, {headers: headers})
  }

  createGenre(createGenreRequest: CreateGenreRequest) {
    const genreUrl = this.url + "genres";

    const headers = {
      "Content-Type": "application/json" || '',
      "Accept": "application/json" || '',
      "Access-Control-Allow-Headers": "Content-Type" || '',
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.post(genreUrl, createGenreRequest, {headers: headers})
  }
}
