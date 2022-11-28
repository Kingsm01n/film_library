import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AdminActorsService} from "./admin-actors.service";
import {CreateActorRequest} from "./create-actor.request";

@Component({
  selector: 'registration-component',
  templateUrl: './admin-actors.html',
  styleUrls: ['./admin-actors.css', '../admin-header/admin.header.css'],
  providers: [AdminActorsService]
})
export class AdminActorsComponent {

  actors: any[];

  createActorRequest: CreateActorRequest;

  public video: FormData;
  public trailer: FormData;
  public image: FormData;

  constructor(private serv: AdminActorsService, private router: Router) {
    this.createActorRequest = new CreateActorRequest();

    this.serv.getActors().subscribe((data: any) => this.actors = data)
  }

  createActor() {
    this.serv.createActor(this.createActorRequest).subscribe((data: any) => {
      return this.actors.push(data)
    });
  }
}
