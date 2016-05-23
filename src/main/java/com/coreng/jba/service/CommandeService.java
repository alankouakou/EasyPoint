package com.coreng.jba.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coreng.jba.entities.Client;
import com.coreng.jba.entities.Commande;
import com.coreng.jba.entities.LigneCommande;
import com.coreng.jba.repositories.ClientRepository;
import com.coreng.jba.repositories.CommandeRepository;
import com.coreng.jba.repositories.LigneCommandeRepository;

@Service
@Transactional
public class CommandeService {

	@Autowired
	private CommandeRepository commandeRepo;

	@Autowired
	private ClientRepository clientRepo;

	private LigneCommandeRepository ligneCommandeRepo;

	public List<Commande> findByClient(Client client) {
		return commandeRepo.findByClient(client);
	}

	public List<Commande> findAll() {
		return commandeRepo.findAll();
	}

	public void save(Commande commande) {
		commandeRepo.save(commande);
	}

	public Commande findById(Long id) {
		return commandeRepo.findById(id);
	}

	public Commande findOne(Long id) {
		return commandeRepo.findOne(id);
	}

	public Commande findOneWithDetails(Long id) {
		Commande commande = findOne(id);
		System.out.println(commande);
		List<LigneCommande> lignes = commande.getLigneCommandes();
		if (lignes != null) {
			System.out.println(lignes);
			commande.setLigneCommandes(lignes);
			// commande.setClient(commandeRepo.findClientById(id));
		} else {
			lignes = new ArrayList<LigneCommande>();
		}
		return commande;
	}

}
