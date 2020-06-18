//tslint:disable
export class ProjetFilter {
  public acheter_bien = true
  public louer_bien = false
  public maison_bien = false
  public appartement_bien = false
  public terrain_bien = false
  public parking_bien = false
}
export class LieuxFilter {
  public temp = ''
  public transport = ''
  public villes = []
}
export class BudgetFilter {
  public min_budget = 0
  public max_budget = 0
}
export class SurfaceFilter {
  public min_surface_habitable = 0
  public max_surface_habitable = 0
  public min_surface_terrain = 0
  public max_surface_terrain = 0
}
export class PiecesFilter {
  public pieces_studio = false
  public pieces_2 = false
  public pieces_3 = false
  public pieces_4 = false
  public pieces_5 = false
  public chambres_1 = false
  public chambres_2 = false
  public chambres_3 = false
  public chambres_4 = false
  public chambres_5 = false
}
export class Filters {
  public projets: ProjetFilter;
  public lieux: LieuxFilter;
  public budget: BudgetFilter;
  public surface: SurfaceFilter;
  public pieces: PiecesFilter;

  constructor(){
    this.projets = new ProjetFilter()
    this.lieux = new LieuxFilter()
    this.budget = new BudgetFilter()
    this.surface = new SurfaceFilter()
    this.pieces = new PiecesFilter()
  }
}
