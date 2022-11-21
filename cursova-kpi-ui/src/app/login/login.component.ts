import {Component} from '@angular/core';
import {LoginService} from './login.service';
import {UserLoginRequest} from './user.login.request';
import {Router} from '@angular/router';

@Component({
  selector: 'login-component',
  templateUrl: 'login.html',
  styleUrls: ['login.css'],
  providers: [LoginService]
})
export class LoginComponent {

  user: UserLoginRequest;
  username: string | undefined;

  constructor(private serv: LoginService, private router: Router) {
    this.user = new UserLoginRequest();
  }

  login() {
    console.log(this.user)
    this.serv.login(this.user).subscribe((data: any) => {
      console.log(data)
      if (data.status == "OK") {
        this.router.navigate(["tournaments"])
      } else {
        alert(data.message)
      }
    })
  }
}
