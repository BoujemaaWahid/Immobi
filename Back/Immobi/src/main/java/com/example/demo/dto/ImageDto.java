package com.example.demo.dto;

import java.io.Serializable;
import com.example.demo.entitys.Local;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDto implements Serializable{

	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private Long id;
	@JsonProperty("base64")
	private String base64;
	@JsonProperty("local")
	private Local local;

}
