package com.coreng.jba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coreng.jba.entities.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

	Vehicule findByImmatriculation(String immatriculation);

	@Override
	@Query("select v from Vehicule v order by v.marque")
	List<Vehicule> findAll();

}
