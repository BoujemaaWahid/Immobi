package com.example.demo.entitys;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Images")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "base64")
	private String base64;

	@ManyToOne
	@JoinColumn(name="local_id", nullable = false)
	@JsonIgnore
	private Local local;
	
}
