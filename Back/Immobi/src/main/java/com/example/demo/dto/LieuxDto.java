package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entitys.Adresse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LieuxDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("label")
    private String label;
	
	@JsonProperty("postal")
    private String code_postal;
	
	@JsonProperty("adresses")
	private List<Adresse> adresses = new ArrayList<Adresse>();
	
	public LieuxDto() {}
	
	public LieuxDto(String label, String code_postal) {
		this.label = label;
		this.code_postal = code_postal;
	}
	
	public LieuxDto(String label, String code_postal, List<Adresse> adresses) {
		this(label, code_postal);
		this.adresses = adresses;
	}

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

	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public List<Adresse> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
	@Override
	public String toString() {
		return "LieuxDto [id=" + id + ", label=" + label + ", code_postal=" + code_postal + ", adresses=" + adresses
				+ "]";
	}	

	
}

