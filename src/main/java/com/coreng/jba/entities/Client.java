package com.coreng.jba.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nom;
	private String contact;
	private Date dateMaj;
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vehicule> vehicules;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Vehicule> getVehicules() {
		return vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

	public void addVehicule(Vehicule vehicule) {
		vehicule.setOwner(this);
		vehicules.add(vehicule);
	}

	public void removeVehicule(Vehicule vehicule) {
		vehicule.setOwner(null);
		this.vehicules.remove(vehicule);
	}

	public Client() {
		this.vehicules = new ArrayList<Vehicule>();
	}

	public Client(String nom, String contact) {
		this.nom = nom;
		this.contact = contact;
		this.vehicules = new ArrayList<Vehicule>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getDateMaj() {
		return dateMaj;
	}

	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}

	@Override
	public String toString() {
		return String.format("Client id: %s, nom: %s, contact: %s", id, nom, contact);
	}

}
