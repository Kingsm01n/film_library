import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {CreateGenreRequest} from "./create-genre.request";
import {AdminGenresService} from "./admin-genres.service";

@Component({
  selector: 'registration-component',
  templateUrl: './admin-genres.html',
  styleUrls: ['./admin-genres.css', '../admin-header/admin.header.css'],
  providers: [AdminGenresService]
})
export class AdminGenresComponent {

  genres: any[];

  createGenreRequest: CreateGenreRequest;

  public video: FormData;
  public trailer: FormData;
  public image: FormData;

  constructor(private serv: AdminGenresService, private router: Router) {
    this.createGenreRequest = new CreateGenreRequest();

    this.serv.getGenres().subscribe((data: any) => this.genres = data)
  }

  createGenre() {
    this.serv.createGenre(this.createGenreRequest).subscribe((data: any) => {
      return this.genres.push(data)
    });
  }
}
