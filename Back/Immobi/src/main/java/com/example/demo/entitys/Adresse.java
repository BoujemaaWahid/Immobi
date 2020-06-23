package com.example.demo.entitys;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "adresses")
public class Adresse {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="RUE", length=50, nullable=false)
    private String rue;
	
	@Column(name="NUMERO")
    private int numero;
	
	@Column(name="LATITUDE", nullable=false, precision = 10, scale = 0)
    private double latitude;
	
	@Column(name="LONGITUDE", nullable=false, precision = 10, scale = 0)
    private double longitude;

	
	@ManyToOne
	@JoinColumn(name="lieu_id", nullable = false)
	@JsonIgnore
	Lieux lieu;
	
	public Adresse() {}
	
	public Adresse(String rue, int numero, double latitude, double longitude, Lieux lieu) {
		this.rue = rue;
		this.numero = numero;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lieu = lieu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Lieux getLieu() {
		return lieu;
	}

	public void setLieu(Lieux lieu) {
		this.lieu = lieu;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", rue=" + rue + ", numero=" + numero + ", latitude=" + latitude + ", longitude="
				+ longitude + ", lieu=" + lieu + "]";
	}
	
}
