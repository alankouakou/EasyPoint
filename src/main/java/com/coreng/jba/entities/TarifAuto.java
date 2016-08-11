package com.coreng.jba.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TarifAuto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private Integer prix;

	@ManyToOne
	@JoinColumn(name = "typevehicule_id")
	private TypeVehicule typeVehicule;

	public TarifAuto() {
	}

	public TarifAuto(String name, Integer prix, TypeVehicule typeVehicule) {
		this.name = "";
		this.prix = 0;
		this.typeVehicule = typeVehicule;
	}

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

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	public TypeVehicule getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(TypeVehicule typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

}
