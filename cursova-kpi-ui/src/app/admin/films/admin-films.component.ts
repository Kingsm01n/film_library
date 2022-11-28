import {Component, Renderer2} from '@angular/core';
import {Router} from '@angular/router';
import {AdminFilmsService} from "./admin-films.service";

@Component({
  selector: 'registration-component',
  templateUrl: './admin-films.html',
  styleUrls: ['./admin-films.css', '../admin-header/admin.header.css'],
  providers: [AdminFilmsService]
})
export class AdminFilmsComponent {

  films: any[];
  genres: Map<number, any> = new Map<number, any>();
  actors: Map<number, any> = new Map<number, any>();

  constructor(private serv: AdminFilmsService, private router: Router, private renderer: Renderer2) {
    this.serv.loadFilms().subscribe((data: any) => {
      this.serv.getGenres().subscribe((genres: any) => {
        genres.forEach((item: any) => {
          this.genres.set(item.id, item.name);
        });
        data.forEach((item: any) => {
          item.imgSrc = 'data:image/bmp;base64,' + item.image;
          if (item.genreIds) {
            item.genresNames = [];
            item.genreIds.forEach((genreId: number) => {
              item.genresNames.push(this.genres.get(genreId));
            })
          }
        });
      });
      this.serv.getActors().subscribe((actors: any) => {
        actors.forEach((item: any) => {
          this.actors.set(item.id, item.name);
        });
        data.forEach((item: any) => {
          if (item.actorIds) {
            item.actorsNames = [];
            item.actorIds.forEach((actorId: any) => {
              item.actorsNames.push(this.genres.get(actorId));
            })
          }
        });
      });

      data.forEach((item: any) => {
        if(item.image) {
          item.imgSrc = 'data:image/bmp;base64,' + item.image;
        }
      })
      this.films = data;
    });
  }

  edit(film: any) {
    this.router.navigate(["admin/films/edit", {filmId: film.id}]);
  }
}
