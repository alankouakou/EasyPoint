package com.coreng.jba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coreng.jba.entities.Commande;
import com.coreng.jba.entities.Consommation;
import com.coreng.jba.entities.LigneCommande;
import com.coreng.jba.repositories.ConsommationRepository;
import com.coreng.jba.repositories.LigneCommandeRepository;

@Service
@Transactional
public class DetailCommandeService {

	@Autowired
	private LigneCommandeRepository ligneCommandeRepo;

	@Autowired
	private ConsommationRepository consommationRepo;

	public List<LigneCommande> findByCommande(Commande commande) {
		return ligneCommandeRepo.findByCommande(commande);
	}

	public void save(LigneCommande ligneCommande) {
		Consommation conso;
		int qte;
		int prix;

		conso = consommationRepo.findOne(ligneCommande.getConsommation().getId());
		prix = conso.getPrixConso();
		qte = ligneCommande.getQuantite();
		ligneCommande.setPrixUnitaire(prix);
		ligneCommande.setMontant(prix * qte);
		int NlleQte = conso.getQteEnStock() - qte;
		// Met à jour le stock si la qté restante est > 0
		if (NlleQte >= 0) {
			conso.setQteEnStock(NlleQte);
			ligneCommandeRepo.save(ligneCommande);
			consommationRepo.save(conso);
		}
	}

	public void save(LigneCommande ligneCommande, Commande commande) {
		ligneCommande.setCommande(commande);
		ligneCommandeRepo.save(ligneCommande);
	}

	public Long totalCommandes() {
		return ligneCommandeRepo.totalCommandes();
	};

}
