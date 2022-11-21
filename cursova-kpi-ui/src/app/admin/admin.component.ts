import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AdminService} from "./admin.service";
import {CreateFilmRequest} from "./create.film.request";
import {UserLoginRequest} from "../login/user.login.request";

@Component({
  selector: 'registration-component',
  templateUrl: './admin.html',
  styleUrls: ['./admin.css'],
  providers: [AdminService]
})
export class AdminComponent {

  createFilmRequest: CreateFilmRequest;

  constructor(private serv: AdminService, private router: Router) {
    this.createFilmRequest = new CreateFilmRequest();
  }

  createFilm() {
    console.log(this.createFilmRequest)
    if(this.createFilmRequest) {
      this.serv.createFilm(this.createFilmRequest).subscribe((data: any) => {
        console.log(data)
        if (data.status == "OK") {
          this.router.navigate(["tournaments"])
        } else {
          alert(data.message)
        }
      });
    }
  }
}
