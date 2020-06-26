package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entitys.Adresse;
import com.example.demo.entitys.Region;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LieuxDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("label")
    private String label;
	
	@JsonProperty("postal")
    private String code_postal;
	
	@JsonProperty("region")
	private Region region;
	
	@JsonProperty("adresses")
	private List<Adresse> adresses = new ArrayList<Adresse>();
	
}

