package com.example.demo.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REGION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Region {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "label", nullable = false, unique = true)
	private String label;
	
	
	@OneToMany(mappedBy = "region")
	@JsonIgnore
	List<Lieux> lieux;

	@Override
	public String toString() {
		return "Region [id=" + id + ", label=" + label + ", lieux=" + lieux + "]";
	}
	
}
