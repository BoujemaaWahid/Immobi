package com.example.demo.dto;

import java.io.Serializable;
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
public class ImageDto implements Serializable{

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long id;
	@JsonProperty("base64")
	private String base64;
	@JsonProperty("local")
	private Local local;

	@Override
	public String toString() {
		return "Image [id=" + id + ", base64=" + base64 + ", local=" + local + "]";
	}

}
