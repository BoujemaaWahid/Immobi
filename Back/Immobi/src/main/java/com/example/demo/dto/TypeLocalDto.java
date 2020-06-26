package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;
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
public class TypeLocalDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private long id;
	@JsonProperty("label")
	private String label;
	@JsonProperty("locales")
	private List<Local> locales;

	@Override
	public String toString() {
		return "TypeLocal [id=" + id + ", label=" + label + ", locales=" + locales + "]";
	}

}
