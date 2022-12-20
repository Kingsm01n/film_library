import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CreateFilmRequest} from "./create-film.request";

@Injectable()
export class AdminCreateFilmService {

  private url: string = "http://172.25.112.128/api/v1/";

  constructor(private http: HttpClient) {
  }

  createFilm(createFilmRequest: CreateFilmRequest) {
    const filmUrl = this.url + "films";

    const headers = {
      "Content-Type": "application/json" || '',
      "Accept": "application/json" || '',
      "Access-Control-Allow-Headers": "Content-Type" || '',
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    createFilmRequest.genreIds = createFilmRequest.genreIds.filter(item => item != null);

    return this.http.post(filmUrl, createFilmRequest, {headers: headers})
  }

  uploadVideo(id: number, video: FormData) {
    const uploadVideoUrl = this.url + "upload/films/" + id + "/video";

    const headers = {
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.put(uploadVideoUrl, video, requestOptions)
  }

  uploadTrailer(id: number, trailer: FormData) {
    const uploadVideoUrl = this.url + "upload/films/" + id + "/trailer";

    const headers = {
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.put(uploadVideoUrl, trailer, requestOptions)
  }

  uploadImage(id: number, image: FormData) {
    const uploadVideoUrl = this.url + "upload/films/" + id + "/image";

    const headers = {
      "Authorization": localStorage.getItem("Authorization")  || ''
    }

    const requestOptions = {
      headers: new HttpHeaders(headers),
    };

    return this.http.put(uploadVideoUrl, image, requestOptions)
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
}
