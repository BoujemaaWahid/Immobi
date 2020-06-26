package com.example.demo.entitys;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "adresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Adresse {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="RUE", length=50, nullable=false)
    private String rue;
	
	@Column(name="NUMERO")
    private int numero;
	
	@Column(name="COMPLEMENT")
	String complement;
	
	@Column(name="LATITUDE", nullable=false, precision = 10, scale = 0)
    private double latitude;
	
	@Column(name="LONGITUDE", nullable=false, precision = 10, scale = 0)
    private double longitude;

	
	@ManyToOne
	@JoinColumn(name="lieu_id", nullable = false)
	@JsonIgnore
	Lieux lieu;
	
	@OneToMany(mappedBy = "adresse")
	@JsonIgnore
	List<Local> locales = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", rue=" + rue + ", numero=" + numero + ", complement=" + complement
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", lieu=" + lieu + ", locales=" + locales
				+ "]";
	}
	
}
