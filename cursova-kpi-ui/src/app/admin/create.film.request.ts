export class CreateFilmRequest {

  public name: string | undefined;
  public description: string | undefined;
  public rating: number | undefined;
  public genres: number[] | undefined;
  public actors: number[] | undefined;
  public film: File | undefined;
  public trailer: File | undefined;
  public image: File | undefined;

  constructor() { }
}
