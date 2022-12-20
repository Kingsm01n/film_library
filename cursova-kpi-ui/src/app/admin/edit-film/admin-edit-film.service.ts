import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class AdminEditFilmService {

  private url: string = "http://172.25.112.128/api/v1/";

  constructor(private http: HttpClient) {
  }

  getGenres() {
    const genresUrl = this.url + "genres";

    const headers = {
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.get(genresUrl, requestOptions);
  }

  getActors() {
    const actorsUrl = this.url + "actors";

    const headers = {
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.get(actorsUrl, requestOptions);
  }

  getFilmById(filmId: any) {
    const url = this.url + "films/" + filmId;
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
}
