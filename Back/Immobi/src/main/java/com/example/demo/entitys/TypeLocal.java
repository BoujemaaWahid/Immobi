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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TypeLocal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "label", nullable = false, unique = true)
	private String label;
	
	@ManyToMany(mappedBy = "types")
	@JsonIgnore
	private List<Local> locales;
	
	@Override
	public String toString() {
		return "TypeLocal [id=" + id + ", label=" + label + ", locales=" + locales + "]";
	}
}
