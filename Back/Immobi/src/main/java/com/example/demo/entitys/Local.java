package com.example.demo.entitys;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LOCAL")
public class Local {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="adresse_id", nullable = false)
	@JsonIgnore
	private Adresse adresse;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "prix", nullable = false)
	private double prix;
	
	@ManyToMany
	@JoinTable(
	  name = "locales_types", 
	  joinColumns = @JoinColumn(name = "local_id"), 
	  inverseJoinColumns = @JoinColumn(name = "type_id"))
	@JsonIgnore
	private List<TypeLocal> types;


	@OneToMany(mappedBy = "local")
	@JsonIgnore
	private List<Image> images;
	
	@Column(name = "surface", nullable = false)
	private double surface;
	
	@Column(name="surface_terrain")
	private double surface_terrain;
	
	@Column(name = "num_pieces", nullable = false)
	private int num_pieces;
	
	@Column(name="num_chambres", nullable = false)
	private int num_chambres;
	
	@Column(name = "achat_loue", nullable = false)
	private boolean projet;
	
	@Column(name = "date_publication")
	private Date date_publication;


	public Local() {}
	
	public Local(Adresse adresse, String description, double prix, List<TypeLocal> types, double surface,
			double surface_terrain, int num_pieces, int num_chambres, boolean projet, Date date_publication) {
		super();
		this.adresse = adresse;
		this.description = description;
		this.prix = prix;
		this.types = types;
		this.surface = surface;
		this.surface_terrain = surface_terrain;
		this.num_pieces = num_pieces;
		this.num_chambres = num_chambres;
		this.projet = projet;
		this.date_publication = date_publication;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public List<TypeLocal> getTypes() {
		return types;
	}

	public void setTypes(List<TypeLocal> types) {
		this.types = types;
	}

	public double getSurface() {
		return surface;
	}

	public void setSurface(double surface) {
		this.surface = surface;
	}

	public double getSurface_terrain() {
		return surface_terrain;
	}

	public void setSurface_terrain(double surface_terrain) {
		this.surface_terrain = surface_terrain;
	}

	public int getNum_pieces() {
		return num_pieces;
	}

	public void setNum_pieces(int num_pieces) {
		this.num_pieces = num_pieces;
	}

	public int getNum_chambres() {
		return num_chambres;
	}

	public void setNum_chambres(int num_chambres) {
		this.num_chambres = num_chambres;
	}

	public boolean isProjet() {
		return projet;
	}

	public void setProjet(boolean projet) {
		this.projet = projet;
	}

	public Date getDate_publication() {
		return date_publication;
	}

	public void setDate_publication(Date date_publication) {
		this.date_publication = date_publication;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Local [id=" + id + ", adresse=" + adresse + ", description=" + description + ", prix=" + prix
				+ ", types=" + types + ", images=" + images + ", surface=" + surface + ", surface_terrain="
				+ surface_terrain + ", num_pieces=" + num_pieces + ", num_chambres=" + num_chambres + ", projet="
				+ projet + ", date_publication=" + date_publication + "]";
	}


	

}
