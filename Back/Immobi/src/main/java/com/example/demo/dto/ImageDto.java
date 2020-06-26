package com.example.demo.dto;

import java.io.Serializable;
import com.example.demo.entitys.Local;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageDto implements Serializable{

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long id;
	@JsonProperty("base64")
	private String base64;
	@JsonProperty("local")
	private Local local;
	
	public ImageDto() {}

	public ImageDto(String base64, Local local) {
		this.base64 = base64;
		this.local = local;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", base64=" + base64 + ", local=" + local + "]";
	}

}
