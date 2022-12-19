import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserRegistrRequest } from './user.registration.request';

@Injectable()
export class RegistrationService {

  private url = "http://localhost:8081/api/v1/auth/signup";
  constructor(private http: HttpClient) { }

  createUser(user: UserRegistrRequest) {
    return this.http.post(this.url, user);
  }
}
