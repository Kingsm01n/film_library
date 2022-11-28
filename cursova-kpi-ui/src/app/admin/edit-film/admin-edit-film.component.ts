import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AdminEditFilmService} from "./admin-edit-film.service";
import {EditFilmRequest} from "./edit-film.request";
import {switchMap} from "rxjs";

@Component({
  selector: 'registration-component',
  templateUrl: './admin-edit-film.html',
  styleUrls: ['./admin-edit-film.css', '../admin-header/admin.header.css'],
  providers: [AdminEditFilmService]
})
export class AdminEditFilmComponent {

  editFilmRequest: EditFilmRequest;
  filmId: any;

  genres: any[];
  actors: any[];

  public video: FormData;
  public trailer: FormData;
  public image: FormData;

  constructor(private serv: AdminEditFilmService, private router: Router, private route: ActivatedRoute) {
    this.editFilmRequest = new EditFilmRequest();
    this.filmId = this.route.snapshot.paramMap.get('filmId');
    this.serv.getFilmById(this.filmId).subscribe((data: any) => {
      console.log(data)
      this.editFilmRequest.name = data.name;
      this.editFilmRequest.description = data.description;
      this.editFilmRequest.actorIds = data.actorIds;
      this.editFilmRequest.genreIds = data.genreIds;
      this.editFilmRequest.rating = data.rating;
      this.video = data.video;
      this.trailer = data.trailer;
      this.image = data.image;
    })

    this.serv.getGenres().subscribe((data: any) => this.genres = data)
    this.serv.getActors().subscribe((data: any) => this.actors = data)
  }

  editFilm() {

  }

  handleVideoInput(event: any) {
    const file:File = event.target.files[0];
    const formData = new FormData();
    formData.append("file", file, "file");
    this.video = formData;
  }

  handleTrailerInput(event: any) {
    const file:File = event.target.files[0];
    const formData = new FormData();
    formData.append("file", file, "file");
    this.trailer = formData;
  }

  handleImageInput(event: any) {
    const file:File = event.target.files[0];
    const formData = new FormData();
    formData.append("file", file, "file");
    this.image = formData;
  }

  checkboxChangeGenres(id: number, target: any) {
    if (target.checked) {
      this.editFilmRequest.genreIds.push(id);
    } else {
      const index = this.editFilmRequest.genreIds.indexOf(id);
      this.editFilmRequest.genreIds.splice(index, 1)
    }
  }

  checkboxChangeActors(id: number, target: any) {
    if (target.checked) {
      this.editFilmRequest.actorIds.push(id);
    } else {
      const index = this.editFilmRequest.actorIds.indexOf(id);
      this.editFilmRequest.actorIds.splice(index, 1)
    }
  }

  searchGenres() {
    // Declare variables
    var input, filter, ul, li, a, label, i, txtValue;
    input = document.getElementById('myInputGenres');
    // @ts-ignore
    filter = input.value.toUpperCase();
    ul = document.getElementById("myULGenres");
    // @ts-ignore
    li = ul.getElementsByTagName('li');

    // Loop through all list items, and hide those who don't match the search query
    for (i = 0; i < li.length; i++) {
      a = li[i].getElementsByTagName("a")[0];
      label = a.getElementsByTagName("label")[0];
      txtValue = label.textContent || label.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        li[i].style.display = "";
      } else {
        li[i].style.display = "none";
      }
    }
  }

  searchActors() {
    // Declare variables
    var input, filter, ul, li, a, label, i, txtValue;
    input = document.getElementById('myInputActors');
    // @ts-ignore
    filter = input.value.toUpperCase();
    ul = document.getElementById("myULActors");
    // @ts-ignore
    li = ul.getElementsByTagName('li');

    // Loop through all list items, and hide those who don't match the search query
    for (i = 0; i < li.length; i++) {
      a = li[i].getElementsByTagName("a")[0];
      label = a.getElementsByTagName("label")[0];
      txtValue = label.textContent || label.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        li[i].style.display = "";
      } else {
        li[i].style.display = "none";
      }
    }
  }
}
