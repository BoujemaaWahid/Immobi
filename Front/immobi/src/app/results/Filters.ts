//tslint:disable
export class ProjetFilter {
  public acheter_bien = true
  public louer_bien = false
  public maison_bien = false
  public appartement_bien = false
  public terrain_bien = false
  public parking_bien = false

  public getTypes(){
    let l = [
      (this.appartement_bien)?1:null,
      (this.maison_bien)?2:null,
      (this.parking_bien)?3:null,
      (this.terrain_bien)?4:null
    ]
    l = l.filter(val => {
      return (val != null)
    })
    return l;
  }
}
export class DatePublication {
  public min = ""
  public max = ""
}
export class LieuxFilter {
  public temp = ''
  public transport = ''
  public villes = []

  public getAdresses(){
    let r = []
    this.villes.forEach(item=>{
      const e = item.split("_")
      r.push({"id":e[0], "from":e[1]})
    })
    return r;
  }
}
export class BudgetFilter {
  public min_budget = 0
  public max_budget = 0

  public getPrix(){
    if( this.min_budget == 0 && this.max_budget == 0)return {}
    return {"min": this.min_budget, "max": this.max_budget}
  }
}
export class SurfaceFilter {
  public min_surface_habitable = 0
  public max_surface_habitable = 0
  public min_surface_terrain = 0
  public max_surface_terrain = 0

  public getSurface(){
    if( this.min_surface_habitable == 0 && this.max_surface_habitable == 0)return {}
    return {"min": this.min_surface_habitable, "max": this.max_surface_habitable}
  }
  public getTerrain(){
    if( this.min_surface_terrain == 0 && this.max_surface_terrain == 0)return {}
    return {"min": this.min_surface_terrain, "max": this.max_surface_terrain}
  }
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

  public getPieces(){
    if(this.pieces_studio) return 1;
    if(this.pieces_2) return 2;
    if(this.pieces_3) return 3;
    if(this.pieces_4) return 4;
    if(this.pieces_5) return 5;
    return null;
  }
  public getChambres(){
    if(this.chambres_1) return 1;
    if(this.chambres_2) return 2;
    if(this.chambres_3) return 3;
    if(this.chambres_4) return 4;
    if(this.chambres_5) return 5;
    return null;
  }
}
export class Filters {
  public projets: ProjetFilter;
  public lieux: LieuxFilter;
  public budget: BudgetFilter;
  public surface: SurfaceFilter;
  public pieces: PiecesFilter;
  public dates: DatePublication;

  constructor(){
    this.projets = new ProjetFilter()
    this.lieux = new LieuxFilter()
    this.budget = new BudgetFilter()
    this.surface = new SurfaceFilter()
    this.pieces = new PiecesFilter()
    this.dates = new DatePublication()
  }

  public prepare(){

    let filter = {
      "projet":[this.projets.acheter_bien, this.projets.louer_bien],
      "date":{"min":this.dates.min, "max":this.dates.max},
      "prix": this.budget.getPrix(),
      "types":this.projets.getTypes(),
      "surface": this.surface.getSurface(),
      "terrain": this.surface.getTerrain(),
      "adresses":this.lieux.getAdresses(),
      "pieces": this.pieces.getPieces(),
      "chambres": this.pieces.getChambres()
    }
    return filter;
  }
}
