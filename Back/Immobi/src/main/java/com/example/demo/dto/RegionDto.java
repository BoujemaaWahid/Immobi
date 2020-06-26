package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import com.example.demo.entitys.Lieux;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("label")
	private String label;
	@JsonProperty("lieux")
	private List<Lieux> lieux;

	@Override
	public String toString() {
		return "Region [id=" + id + ", label=" + label + ", lieux=" + lieux + "]";
	}

}
