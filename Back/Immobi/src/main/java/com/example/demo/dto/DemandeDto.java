package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.entitys.Local;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DemandeDto extends MessageDto implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty("tel")
	private String telephone;
	@JsonProperty("local")
	private Local local;
	
}
