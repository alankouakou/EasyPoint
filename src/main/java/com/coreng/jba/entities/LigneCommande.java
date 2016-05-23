package com.coreng.jba.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LigneCommande {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "consommation_id")
	private Consommation consommation;
	private int prixUnitaire;
	private int quantite;
	private int montant;

	@ManyToOne
	@JoinColumn(name = "commande_id")
	private Commande commande;

	public LigneCommande() {
		quantite = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consommation getConsommation() {
		return consommation;
	}

	public void setConsommation(Consommation consommation) {
		this.consommation = consommation;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getMontant() {
		return montant;
	}

	public int getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(int prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

}
