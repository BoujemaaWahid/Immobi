package com.example.demo.extradtos;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("fact_1")
	private String identifiant;
	
	@JsonProperty("fact_2")
	private String password;
	
	@JsonProperty("fact_3")
	private boolean admin;
	
	public IdType idType() {
		String phone = "^((\\+91-?)|0)?[0-9]{10}$";
		String email = "^(.+)@(.+)$";
		if ( Pattern.matches(phone, this.identifiant) ) return IdType.PHONE;
		else if ( Pattern.matches(email, this.identifiant)) return IdType.EMAIL;
		else return IdType.USERNAME;
	}
}
