import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserLoginRequest } from './user.login.request';

@Injectable()
export class LoginService {

  private url = "http://172.25.112.128/api/v1/auth/login";
  constructor(private http: HttpClient) { }

  login(user: UserLoginRequest) {
    return this.http.post(this.url, user);
  }
}
