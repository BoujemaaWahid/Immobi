package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;


import com.example.demo.entitys.Adresse;
import com.example.demo.entitys.Image;
import com.example.demo.entitys.TypeLocal;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
	@JsonProperty("disponible")
	private boolean disponible;
	
}
