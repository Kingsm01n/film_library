import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class AdminFilmsService {

  private url: string = "http://localhost:8080/api/v1/";

  constructor(private http: HttpClient) {
  }

  loadFilms() {
    const url = this.url + "films";
    const headers = {
      "Content-Type": "application/json" || '',
      "Accept": "application/json" || '',
      "Access-Control-Allow-Headers": "Content-Type" || '',
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.get(url, {headers: headers})
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

  getActors() {
    const actorUrl = this.url + "actors";

    const headers = {
      "Content-Type": "application/json" || '',
      "Accept": "application/json" || '',
      "Access-Control-Allow-Headers": "Content-Type" || '',
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.get(actorUrl, {headers: headers})
  }
}
