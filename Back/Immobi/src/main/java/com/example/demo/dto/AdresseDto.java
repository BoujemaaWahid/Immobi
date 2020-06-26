package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.entitys.Lieux;
import com.example.demo.entitys.Local;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdresseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("rue")
	private String rue;
	
	@JsonProperty("numero")
    private int numero;
	
	@JsonProperty("complement")
	private String complement;

	@JsonProperty("lat")
    private double latitude;

	@JsonProperty("lng")
    private double longitude;

	@JsonProperty("lieu")
	private Lieux lieu;
	
	@JsonProperty("locales")
	private List<Local> locales;
	
	@Override
	public String toString() {
		return "AdresseDto [id=" + id + ", rue=" + rue + ", numero=" + numero + ", complement=" + complement
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", lieu=" + lieu + ", locales=" + locales
				+ "]";
	}

}

