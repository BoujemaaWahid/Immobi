package com.example.demo.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TYPE")
public class TypeLocal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "label", nullable = false, unique = true)
	private String label;
	
	@ManyToMany(mappedBy = "types")
	@JsonIgnore
	private List<Local> locales;
	
	public TypeLocal() {}
	
	public TypeLocal(String label, List<Local>locales) {
		this.label = label;
		this.locales = locales;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Local> getLocales() {
		return locales;
	}

	public void setLocales(List<Local> locales) {
		this.locales = locales;
	}

	@Override
	public String toString() {
		return "TypeLocal [id=" + id + ", label=" + label + ", locales=" + locales + "]";
	}
	
	

}
