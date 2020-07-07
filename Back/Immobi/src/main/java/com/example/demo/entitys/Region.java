package com.example.demo.entitys;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REGION")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Region {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "label", nullable = false, unique = true)
	private String label;
	
	
	@OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Lieux> lieux;
	
}
