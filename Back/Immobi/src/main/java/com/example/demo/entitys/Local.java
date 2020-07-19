package com.example.demo.entitys;

import java.sql.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LOCAL")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Local {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titre")
	private String titre;
	
	@ManyToOne
	@JoinColumn(name="adresse_id", nullable = false)
	@JsonIgnore
	private Adresse adresse;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
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

	@ManyToMany(mappedBy = "favoires")
	@JsonIgnore
	private List<User> favoires;
	
	@OneToMany(mappedBy = "local")
	private List<Image> images;
	
	@OneToMany(mappedBy = "local")
	@JsonIgnore
	private List<Demande> demandes;
	
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
	
	@Column(name = "disponible", nullable = false)
	private boolean disponible = true;

}
