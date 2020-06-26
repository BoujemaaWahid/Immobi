package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import com.example.demo.entitys.Lieux;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("label")
	private String label;
	@JsonProperty("lieux")
	private List<Lieux> lieux;

	public RegionDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Lieux> getLieux() {
		return lieux;
	}

	public void setLieux(List<Lieux> lieux) {
		this.lieux = lieux;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", label=" + label + ", lieux=" + lieux + "]";
	}

}
