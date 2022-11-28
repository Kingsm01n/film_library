import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AdminCreateFilmService} from "./admin-create-film.service";
import {CreateFilmRequest} from "./create-film.request";

@Component({
  selector: 'registration-component',
  templateUrl: './admin-create-film.html',
  styleUrls: ['./admin-create-film.css', '../admin-header/admin.header.css'],
  providers: [AdminCreateFilmService]
})
export class AdminCreateFilmComponent {

  genres: any[];
  actors: any[];

  createFilmRequest: CreateFilmRequest;

  public video: FormData;
  public trailer: FormData;
  public image: FormData;

  constructor(private serv: AdminCreateFilmService, private router: Router) {
    this.createFilmRequest = new CreateFilmRequest();
    this.createFilmRequest.genreIds = new Array(10000);

    this.serv.getGenres().subscribe((data: any) => this.genres = data)
    this.serv.getActors().subscribe((data: any) => this.actors = data)
  }

  createFilm() {
    if (this.createFilmRequest) {
      this.serv.createFilm(this.createFilmRequest).subscribe((data: any) => {
        this.serv.uploadVideo(data.id, this.video).subscribe((data: any) => {
        })
        this.serv.uploadTrailer(data.id, this.trailer).subscribe((data: any) => {
        })
        this.serv.uploadImage(data.id, this.image).subscribe((data: any) => {
        })

        this.router.navigate(['admin/films' + data.id])
      });
    }
  }

  handleVideoInput(event: any) {
    console.log(event);
    const file:File = event.target.files[0];
    console.log(file);
    const formData = new FormData();
    formData.append("file", file, "file");
    this.video = formData;
  }

  handleTrailerInput(event: any) {
    console.log(event);
    const file:File = event.target.files[0];
    console.log(file);
    const formData = new FormData();
    formData.append("file", file, "file");
    this.trailer = formData;
  }

  handleImageInput(event: any) {
    console.log(event);
    const file:File = event.target.files[0];
    console.log(file);
    const formData = new FormData();
    formData.append("file", file, "file");
    this.image = formData;
  }

  checkboxChangeGenres(id: number, target: any) {
    if (target.checked) {
      this.createFilmRequest.genreIds.push(id);
    } else {
      const index = this.createFilmRequest.genreIds.indexOf(id);
      this.createFilmRequest.genreIds.splice(index, 1)
    }
  }

  checkboxChangeActors(id: number, target: any) {
    if (target.checked) {
      this.createFilmRequest.actorIds.push(id);
    } else {
      const index = this.createFilmRequest.actorIds.indexOf(id);
      this.createFilmRequest.actorIds.splice(index, 1)
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
