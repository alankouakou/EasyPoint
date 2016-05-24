package com.coreng.jba.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coreng.jba.entities.Client;
import com.coreng.jba.entities.Commande;
import com.coreng.jba.entities.Consommation;
import com.coreng.jba.entities.LigneCommande;
import com.coreng.jba.service.ClientService;
import com.coreng.jba.service.CommandeService;
import com.coreng.jba.service.ConsommationService;
import com.coreng.jba.service.DetailCommandeService;

@Controller
public class CommandeController {

	@Autowired
	private CommandeService commandeService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private DetailCommandeService detailCommandeService;

	@Autowired
	private ConsommationService consommationService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor cde = new CustomDateEditor(format, false);
		binder.registerCustomEditor(Date.class, cde);
		binder.registerCustomEditor(Client.class, new ClientIdEditor(clientService));
		binder.registerCustomEditor(Consommation.class, new ConsommationIdEditor(consommationService));
	}

	@ModelAttribute("commande")
	private Commande newCommande() {
		Commande commande = new Commande();
		return commande;
	}

	@ModelAttribute("ligneCommande")
	private LigneCommande newDetail() {
		LigneCommande ligne = new LigneCommande();
		return ligne;
	}

	@ModelAttribute("clients")
	private List<Client> getClients() {
		return clientService.findAll();
	}

	@ModelAttribute("consommations")
	private List<Consommation> getConso() {
		return consommationService.findAll();
	}

	@ModelAttribute("commandes")
	private List<Commande> getCommandes() {
		return commandeService.findAll();
	}

	@ModelAttribute("totalCommandes")
	private Long getTotalCommandes() {
		return detailCommandeService.totalCommandes();
	}

	@RequestMapping(value = "/commandes", method = RequestMethod.GET)
	public String listCommandes(@ModelAttribute("commandes") List<Commande> commandes) {
		for (Commande commande : commandes) {
			List<LigneCommande> lignes = detailCommandeService.findByCommande(commande);
			commande.setLigneCommandes(lignes);
		}

		return "commandes";
	}

	@RequestMapping(value = "/commandes", method = RequestMethod.POST)
	public String saveCommande(@ModelAttribute("commande") Commande commande) {
		commande.setLigneCommandes(new ArrayList<LigneCommande>());
		commandeService.save(commande);
		return "redirect:/commandes.html";
	}

	@RequestMapping("/commande")
	public String creerCommande() {
		return "detail-commande";
	}

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.GET)
	public String detailCommande(Model model, @PathVariable Long id) {

		Commande commande = commandeService.findOne(id);
		List<LigneCommande> lignes = detailCommandeService.findByCommande(commande);
		commande.setLigneCommandes(lignes);
		model.addAttribute("commande", commande);
		model.addAttribute("lignesCommande", lignes);
		return "detail-commande";
	}

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.POST)
	public String addDetail(@PathVariable("id") Long id, @ModelAttribute("ligneCommande") LigneCommande ligneCommande) {
		Commande commande = commandeService.findById(id);
		ligneCommande.setCommande(commande);
		detailCommandeService.save(ligneCommande);
		return "redirect:/commande/" + id + ".html";
	}

}
