package com.example.demo.entitys;

import java.util.ArrayList;
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
@Table(name = "LIEUX")
public class Lieux {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="LABEL", length=50, nullable=false, unique=true)
    private String label;
	
	@Column(name="POSTAL", length=50, nullable=false, unique=true)
    private String code_postal;
	
	@OneToMany(mappedBy = "lieu")
	@JsonIgnore
	private List<Adresse> adresses = new ArrayList<Adresse>();
	
	public Lieux() {}
	public Lieux(String label, String code_postal) {
		this.label = label;
		this.code_postal = code_postal;
	}
	public Lieux(String label, String code_postal, List<Adresse> adresses) {
		this(label, code_postal);
		this.adresses = adresses;
	}

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

	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
	@Override
	public String toString() {
		return "Lieux [id=" + id + ", label=" + label + ", code_postal=" + code_postal + ", adresses=" + adresses + "]";
	}

}
