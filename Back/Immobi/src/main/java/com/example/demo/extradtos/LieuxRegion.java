package com.example.demo.extradtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LieuxRegion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("label")
	private String label;
	@JsonProperty("flag")
	private String flag;
	
	public LieuxRegion() {}
	
	public LieuxRegion(Long id, String label, String flag) {
		this.id = id;
		this.label = label;
		this.flag = flag;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "LieuxRegion [id=" + id + ", label=" + label + ", flag=" + flag + "]";
	}
	
	
	
}
