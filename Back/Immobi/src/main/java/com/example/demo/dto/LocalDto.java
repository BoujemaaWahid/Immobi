package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import com.example.demo.entitys.Adresse;
import com.example.demo.entitys.Image;
import com.example.demo.entitys.TypeLocal;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocalDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("adresse")
	private Adresse adresse;
	@JsonProperty("description")
	private String description;
	@JsonProperty("prix")
	private double prix;
	@JsonProperty("types")
	private List<TypeLocal> types;
	@JsonProperty("images")
	private List<Image> images;
	@JsonProperty("surface")
	private double surface;
	@JsonProperty("surface_terrain")
	private double surface_terrain;
	@JsonProperty("pieces")
	private int num_pieces;
	@JsonProperty("chambres")
	private int num_chambres;
	@JsonProperty("projet")
	private boolean projet;
	@JsonProperty("date_pub")
	private String date_publication;


	public LocalDto() {}
	
	public LocalDto(Adresse adresse, String description, double prix, List<TypeLocal> types, double surface,
			double surface_terrain, int num_pieces, int num_chambres, boolean projet, String date_publication) {
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

	public String getDate_publication() {
		return date_publication;
	}

	public void setDate_publication(String date_publication) {
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
