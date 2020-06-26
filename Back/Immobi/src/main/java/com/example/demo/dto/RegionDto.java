package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import com.example.demo.entitys.Lieux;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("label")
	private String label;
	@JsonProperty("lieux")
	private List<Lieux> lieux;

}
