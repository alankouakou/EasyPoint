package com.coreng.jba.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TypeVehicule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String libelle;

	@OneToMany(mappedBy = "typeVehicule")
	private List<TarifAuto> tarifs;

	public TypeVehicule() {
		super();
	}

	public TypeVehicule(String name) {
		this.libelle = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<TarifAuto> getTarifs() {
		return tarifs;
	}

	public void setTarifs(List<TarifAuto> tarifs) {
		this.tarifs = tarifs;
	}

}
