import {Component} from '@angular/core';
import {LoginService} from './login.service';
import {UserLoginRequest} from './user.login.request';
import {Router} from '@angular/router';
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'login-component',
  templateUrl: 'login.html',
  styleUrls: ['login.css'],
  providers: [LoginService]
})
export class LoginComponent {

  user: UserLoginRequest;

  constructor(private serv: LoginService, private router: Router) {
    this.user = new UserLoginRequest();
  }

  login() {
    console.log(this.user)
    this.serv.login(this.user).subscribe((data: any) => {
      console.log(data)
      localStorage.setItem("Authorization", "Bearer " + data.token)
      if (data.role == "ADMIN") {
        this.router.navigate(["admin/films"])
      } else {
        this.router.navigate(["home"])
      }
    })
  }
}
