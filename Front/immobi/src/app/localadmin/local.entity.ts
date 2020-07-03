//tslint:disable
export class Local {
  public id = ""
  public titre = ""
	public adresse = "";
	public description = "";

	public prix = "";
	public type = null;
	public images = null;
	public surface = 0;
	public surface_terrain = 0;

	public pieces = 0;
	public chambres = 0;
	public projet = false;
	public date_pub = "";
  public disponible = true;

  public clear(){
    this.id = null;
    this.titre = "";
    this.adresse = null;
    this.description = "";
    this.prix = "";
    this.type = null;
    this.images = null;
	  this.surface = 0;
	  this.surface_terrain = 0;
    this.pieces = 0;
    this.chambres = 0;
    this.projet = false;
    this.date_pub = "";
    this.disponible = true;
  }
}
