package com.coreng.jba.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Prestation {

	public Prestation() {
	};

	public Prestation(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "prestation")
	private List<TypeConso> typesConso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TypeConso> getTypesConso() {
		return typesConso;
	}

	public void setTypesConso(List<TypeConso> typesConso) {
		this.typesConso = typesConso;
	}

}
