package com.coreng.jba.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coreng.jba.entities.Client;
import com.coreng.jba.entities.Commande;
import com.coreng.jba.entities.Consommation;
import com.coreng.jba.entities.LigneCommande;
import com.coreng.jba.entities.Prestation;
import com.coreng.jba.entities.Role;
import com.coreng.jba.entities.TarifAuto;
import com.coreng.jba.entities.TypeConso;
import com.coreng.jba.entities.TypeVehicule;
import com.coreng.jba.entities.User;
import com.coreng.jba.entities.Vehicule;
import com.coreng.jba.repositories.ClientRepository;
import com.coreng.jba.repositories.CommandeRepository;
import com.coreng.jba.repositories.ConsommationRepository;
import com.coreng.jba.repositories.LigneCommandeRepository;
import com.coreng.jba.repositories.PrestationRepository;
import com.coreng.jba.repositories.RoleRepository;
import com.coreng.jba.repositories.TarifAutoRepository;
import com.coreng.jba.repositories.TypeConsoRepository;
import com.coreng.jba.repositories.TypeVehiculeRep;
import com.coreng.jba.repositories.UserRepository;
import com.coreng.jba.repositories.VehiculeRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private CommandeRepository commandeRepo;

	@Autowired
	private LigneCommandeRepository ligneCommandeRepo;

	@Autowired
	private ConsommationRepository consoRep;

	@Autowired
	private TypeConsoRepository typeConsoRep;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ClientRepository clientRepo;

	@Autowired
	private VehiculeRepository vehiculeRepo;

	@Autowired
	private TypeVehiculeRep typeVehiculeRepo;

	@Autowired
	private PrestationRepository prestationRepo;

	@Autowired
	private RoleRepository roleRep;

	@Autowired
	TarifAutoRepository tarifAutoRepo;

	@PostConstruct
	protected void init() {

		Prestation prestationResto = new Prestation("Restauration");
		Prestation prestationAuto = new Prestation("Service Auto");
		Prestation boisson = new Prestation("Rafraichissement");

		prestationRepo.save(prestationResto);
		prestationRepo.save(prestationAuto);
		prestationRepo.save(boisson);

		// Les rafraichissements
		TypeConso eauMinerale = new TypeConso("Eau minerale", boisson);
		TypeConso soda = new TypeConso("Soda", boisson);
		TypeConso boissonEnergisante = new TypeConso("Boisson energisante", boisson);
		TypeConso biere = new TypeConso("Bière", boisson);
		TypeConso vin = new TypeConso("Vin", boisson);
		TypeConso liqueur = new TypeConso("Liqueur", boisson);
		TypeConso boissonChaude = new TypeConso("Boisson chaude", boisson);
		TypeConso champagne = new TypeConso("Champagne", boisson);

		// Les services auto
		// TypeConso serviceAuto = new TypeConso("Service auto",
		// prestationAuto);
		// TypeConso serviceResto = new TypeConso("Resto", prestationResto);

		typeConsoRep.save(eauMinerale);
		typeConsoRep.save(soda);
		typeConsoRep.save(boissonEnergisante);
		typeConsoRep.save(boissonChaude);
		typeConsoRep.save(vin);
		typeConsoRep.save(biere);
		typeConsoRep.save(liqueur);
		typeConsoRep.save(champagne);

		Consommation heineken = new Consommation("Heineken", biere, 2000, 50, 25);
		Consommation tuborg = new Consommation("Tuborg", biere, 1000, 50, 35);
		Consommation redBull = new Consommation("Red bull", boissonEnergisante, 2000, 50, 25);
		Consommation codys = new Consommation("Codys", boissonEnergisante, 1500, 50, 35);
		Consommation coca = new Consommation("Coca Cola", soda, 1000, 50, 45);
		Consommation moutoncadet = new Consommation("Mouton Cadet", vin, 12000, 20, 15);
		Consommation puyfromage = new Consommation("Puyfromage", vin, 15000, 20, 15);
		Consommation chenet = new Consommation("Chenet", vin, 8000, 20, 15);

		Consommation chivas = new Consommation("Chivas", liqueur, 40000, 20, 15);
		Consommation jack = new Consommation("Jack Daniels", liqueur, 40000, 20, 15);
		Consommation johnny = new Consommation("Johnny Red", liqueur, 25000, 20, 15);
		Consommation johnnyblack = new Consommation("Johnny Black", liqueur, 40000, 20, 15);
		Consommation doubleblack = new Consommation("Johnny Double Black", liqueur, 25000, 20, 15);
		Consommation moet = new Consommation("Moet", champagne, 50000, 20, 10);
		Consommation perrier = new Consommation("Laurent Perrier", champagne, 50000, 25, 10);

		consoRep.save(heineken);
		consoRep.save(redBull);
		consoRep.save(codys);
		consoRep.save(coca);
		consoRep.save(moutoncadet);
		consoRep.save(puyfromage);
		consoRep.save(chenet);
		consoRep.save(chivas);
		consoRep.save(tuborg);
		consoRep.save(moet);
		consoRep.save(perrier);
		consoRep.save(jack);
		consoRep.save(johnny);
		consoRep.save(johnnyblack);
		consoRep.save(doubleblack);

		Role role_user = new Role("ROLE_USER");
		Role role_admin = new Role("ROLE_ADMIN");

		roleRep.save(role_user);
		roleRep.save(role_admin);

		User dave = new User("dave", "dave123");
		dave.addRole(role_admin);
		dave.addRole(role_user);
		userRepo.save(dave);

		User naomie = new User("naomie", "naomie123");
		naomie.addRole(role_user);
		userRepo.save(naomie);

		User user = new User("user", "user123");
		user.addRole(role_user);
		// user.addRole(role_admin);
		userRepo.save(user);

		User admin = new User("admin", "admin123");
		// admin.addRole(role_user);
		admin.addRole(role_admin);
		userRepo.save(admin);

		// TypeVehicule coupé = new TypeVehicule("Coupé");
		// typeVehiculeRep.save(coupé);
		//
		// TypeVehicule limousine = new TypeVehicule("Limousine");
		// typeVehiculeRep.save(limousine);

		TypeVehicule berline = new TypeVehicule("Berline");
		typeVehiculeRepo.save(berline);

		TypeVehicule suv = new TypeVehicule("SUV");
		typeVehiculeRepo.save(suv);
		// ---------------------------------------
		// ---------------------------------------
		Client c1 = new Client("Alan", "09007718");
		clientRepo.save(c1);

		Vehicule v1 = new Vehicule("7698 GJ 01", "Mercedes CLK", berline, c1);
		vehiculeRepo.save(v1);
		Vehicule v2 = new Vehicule("4737 EN 01", "Mercedes E350", berline, c1);
		vehiculeRepo.save(v2);

		Client c2 = new Client("Marcel", "09009577");
		Vehicule v4 = new Vehicule("9292GX01", "Mercedes Class C 2009", berline, c2);
		clientRepo.save(c2);
		vehiculeRepo.save(v4);

		Client c3 = new Client("Charles", "07835747");
		Vehicule v3 = new Vehicule("9393FY01", "Audi", suv, c3);
		clientRepo.save(c3);
		vehiculeRepo.save(v3);

		Commande cmd1 = new Commande();
		cmd1.setClient(c1);
		commandeRepo.save(cmd1);

		LigneCommande lignec1 = new LigneCommande();
		lignec1.setCommande(cmd1);
		lignec1.setConsommation(heineken);
		lignec1.setQuantite(2);
		lignec1.setMontant(lignec1.getQuantite() * lignec1.getConsommation().getPrixConso());
		ligneCommandeRepo.save(lignec1);

		LigneCommande lignec2 = new LigneCommande();
		lignec2.setCommande(cmd1);
		lignec2.setConsommation(tuborg);
		lignec2.setQuantite(10);
		lignec2.setMontant(lignec2.getQuantite() * lignec2.getConsommation().getPrixConso());
		ligneCommandeRepo.save(lignec2);

		LigneCommande lignec3 = new LigneCommande();
		lignec3.setCommande(cmd1);
		lignec3.setConsommation(redBull);
		lignec3.setQuantite(3);
		lignec3.setMontant(lignec3.getQuantite() * lignec3.getConsommation().getPrixConso());
		ligneCommandeRepo.save(lignec3);

		List<LigneCommande> lignes = new ArrayList<LigneCommande>();
		lignes.add(lignec1);
		lignes.add(lignec3);
		cmd1.setLigneCommandes(lignes);
		commandeRepo.save(cmd1);

		TarifAuto lavageSimpleBerline = new TarifAuto("Lavage simple berline", 1000, berline);
		TarifAuto lavageCompletBerline = new TarifAuto("Lavage complet berline", 2000, berline);
		TarifAuto entretienSiegeBerline = new TarifAuto("Entretien siege berline", 10000, berline);

		tarifAutoRepo.save(lavageSimpleBerline);
		tarifAutoRepo.save(lavageCompletBerline);
		tarifAutoRepo.save(entretienSiegeBerline);

		TarifAuto lavageSimpleSUV = new TarifAuto("Lavage simple 4x4", 1500, suv);
		TarifAuto lavageCompletSUV = new TarifAuto("Lavage complet 4x4", 3000, suv);
		TarifAuto entretienSiegeSUV = new TarifAuto("Entretien siege 4x4", 15000, suv);

		tarifAutoRepo.save(lavageSimpleSUV);
		tarifAutoRepo.save(lavageCompletSUV);
		tarifAutoRepo.save(entretienSiegeSUV);

	}

	public List<Vehicule> findAllVehicles() {
		return vehiculeRepo.findAll();
	}

	public List<Vehicule> findAllOrderByMarque() {
		return vehiculeRepo.findAll();
	}

}
