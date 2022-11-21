import { Component } from '@angular/core';
import { UserRegistrRequest } from './user.registration.request';
import { RegistrationService } from './registration.service';
import { Router } from '@angular/router';

@Component({
  selector: 'registration-component',
  templateUrl: './registration.html',
  styleUrls: ['./registration.css'],
  providers: [RegistrationService]
})
export class RegistrationComponent {

  newUser: UserRegistrRequest;

  constructor(private serv: RegistrationService, private router: Router) {
    this.newUser = new UserRegistrRequest();
  }

  createUser() {
    // äîáàâëÿåì ïîëüçîâàòåëÿ
    this.serv.createUser(this.newUser).subscribe((data: any) => {
      console.log(data);
      if (data.status == "CREATED") {
        this.router.navigate(["login"])
      } else {
        alert(data.message);
      }
    });
  }
}
