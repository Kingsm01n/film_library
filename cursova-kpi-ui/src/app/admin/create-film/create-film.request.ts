export class CreateFilmRequest {

  public name: string | undefined;
  public description: string | undefined;
  public rating: number | undefined;
  public genreIds: number[];
  public actorIds: number[];

  constructor() { }
}
