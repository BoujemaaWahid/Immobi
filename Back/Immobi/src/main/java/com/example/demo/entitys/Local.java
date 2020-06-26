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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LOCAL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

	@Override
	public String toString() {
		return "Local [id=" + id + ", adresse=" + adresse + ", description=" + description + ", prix=" + prix
				+ ", types=" + types + ", images=" + images + ", surface=" + surface + ", surface_terrain="
				+ surface_terrain + ", num_pieces=" + num_pieces + ", num_chambres=" + num_chambres + ", projet="
				+ projet + ", date_publication=" + date_publication + "]";
	}

}
