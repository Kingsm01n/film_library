import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CreateActorRequest} from "./create-actor.request";

@Injectable()
export class AdminActorsService {

  private url: string = "http://172.25.112.128/api/v1/";

  constructor(private http: HttpClient) {
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

  createActor(createActorRequest: CreateActorRequest) {
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

    return this.http.post(actorUrl, createActorRequest, {headers: headers})
  }
}
