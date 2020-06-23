package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.entitys.Lieux;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AdresseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("rue")
	private String rue;
	
	@JsonProperty("numero")
    private int numero;

	@JsonProperty("lat")
    private double latitude;

	@JsonProperty("lng")
    private double longitude;

	@JsonProperty("lieu")
	private Lieux lieu;
	
	public AdresseDto() {}
	
	public AdresseDto(String rue, int numero, double latitude, double longitude, Lieux lieu) {
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
		return "AdresseDto [id=" + id + ", rue=" + rue + ", numero=" + numero + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", lieu=" + lieu + "]";
	}

}

