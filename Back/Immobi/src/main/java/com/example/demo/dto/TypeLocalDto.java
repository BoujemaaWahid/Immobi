package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;


import com.example.demo.entitys.Local;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeLocalDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private long id;
	@JsonProperty("label")
	private String label;
	@JsonProperty("locales")
	private List<Local> locales;
	
	public TypeLocalDto() {}
	
	public TypeLocalDto(String label, List<Local>locales) {
		this.label = label;
		this.locales = locales;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Local> getLocales() {
		return locales;
	}

	public void setLocales(List<Local> locales) {
		this.locales = locales;
	}

	@Override
	public String toString() {
		return "TypeLocal [id=" + id + ", label=" + label + ", locales=" + locales + "]";
	}

}
