package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.example.demo.entitys.Lieux;
import com.example.demo.entitys.Local;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
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

}

