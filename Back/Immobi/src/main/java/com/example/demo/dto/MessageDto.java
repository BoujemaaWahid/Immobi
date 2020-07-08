package com.example.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	private long id;
	@JsonProperty("email")
	private String email;
	@JsonProperty("message")
	private String message;
	@JsonProperty("vue")
	private boolean vue;
}
