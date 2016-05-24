package com.coreng.jba.controller;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coreng.jba.entities.Client;
import com.coreng.jba.entities.TypeVehicule;
import com.coreng.jba.entities.Vehicule;
import com.coreng.jba.service.ClientService;
import com.coreng.jba.service.InitDbService;
import com.coreng.jba.service.TypeVehiculeService;
import com.coreng.jba.service.VehiculeService;

@Controller
public class IndexController {

	@Autowired
	InitDbService initDbService;

	@Autowired
	TypeVehiculeService typeVehiculeService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private VehiculeService vehiculeService;

	@ModelAttribute("vehicule")
	public Vehicule newVehicule() {
		return new Vehicule();
	}

	@ModelAttribute("typeVehicules")
	public List<TypeVehicule> typeVehicules() {
		return typeVehiculeService.findAll();
	}

	@ModelAttribute("clients")
	public List<Client> clients() {
		return clientService.findAll();
	}

	@ModelAttribute("marques")
	public List<String> listMarques() {
		List<String> marques = Arrays.asList("Volkswagen", "Mercedes", "BMW", "Toyota", "Renault", "Peugeot", "Nissan",
				"Audi", "Citroen", "Mitsubishi", "Infinity", "Honda", "Ford", "Porsche", "Jeep", "Range Rover",
				"Hyundai", "Kia", "Mazda", "Volvo", "Chevrolet", "Lexus", "Acura", "Autres");

		return marques;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(TypeVehicule.class, "typeVehicule", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				TypeVehicule tv = typeVehiculeService.findById(Long.parseLong(text));
				setValue(tv);
			}
		});

		binder.registerCustomEditor(Client.class, "owner", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Client clt = clientService.findById(Long.parseLong(text));
				setValue(clt);
			}
		});

	}

	@RequestMapping("/index")
	public String hello() {
		return "index";
	}

	@RequestMapping("/vehicules")
	public String listVehicules(Model model) {
		List<Vehicule> vehicules = initDbService.findAllOrderByMarque();
		model.addAttribute("vehicules", vehicules);

		return "vehicules";
	}

	@RequestMapping(value = "/vehicules", method = RequestMethod.POST)
	public String saveVehicule(@ModelAttribute("vehicule") Vehicule vehicule) {
		System.out.println(vehicule);
		vehiculeService.save(vehicule);
		return "redirect:/vehicules.html";
	}

	@RequestMapping(value = "/register-vehicule", method = RequestMethod.GET)
	public String nouveauVehicule() {
		return "register-vehicule";
	}

	@RequestMapping(value = "/register-vehicule", method = RequestMethod.POST)
	public String enregistrerVehicule(@ModelAttribute("vehicule") Vehicule vehicule) {

		vehiculeService.save(vehicule);
		return "redirect:/vehicules.html";
	}
}
