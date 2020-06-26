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

@Entity
@Table(name = "REGION")
public class Region {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "label", nullable = false, unique = true)
	private String label;
	
	
	@OneToMany(mappedBy = "region")
	@JsonIgnore
	List<Lieux> lieux;

	public Region() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Lieux> getLieux() {
		return lieux;
	}

	public void setLieux(List<Lieux> lieux) {
		this.lieux = lieux;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", label=" + label + ", lieux=" + lieux + "]";
	}
	
}
