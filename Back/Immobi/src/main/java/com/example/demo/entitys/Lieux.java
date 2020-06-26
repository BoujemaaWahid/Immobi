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
@Table(name = "LIEUX")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lieux {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="LABEL", length=50, nullable=false, unique=true)
    private String label;
	
	@Column(name="POSTAL", length=50, nullable=false, unique=true)
    private String code_postal;
	
	@ManyToOne
	@JoinColumn(name="region", nullable = false)
	@JsonIgnore
	private Region region;
	
	@OneToMany(mappedBy = "lieu")
	@JsonIgnore
	private List<Adresse> adresses = new ArrayList<Adresse>();
	
	@Override
	public String toString() {
		return "Lieux [id=" + id + ", label=" + label + ", code_postal=" + code_postal + ", region=" + region
				+ ", adresses=" + adresses + "]";
	}

}
