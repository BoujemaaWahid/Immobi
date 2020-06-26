package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
import com.example.demo.entitys.Local;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class TypeLocalDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private long id;
	@JsonProperty("label")
	private String label;
	@JsonProperty("locales")
	private List<Local> locales;

}
