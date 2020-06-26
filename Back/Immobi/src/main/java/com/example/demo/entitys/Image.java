package com.example.demo.entitys;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "base64")
	private String base64;

	@ManyToOne
	@JoinColumn(name="local_id", nullable = false)
	@JsonIgnore
	private Local local;

	@Override
	public String toString() {
		return "Image [id=" + id + ", base64=" + base64 + ", local=" + local + "]";
	}
	
}
