package com.coreng.jba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coreng.jba.entities.TypeVehicule;
import com.coreng.jba.repositories.TypeVehiculeRep;

@Service
@Transactional
public class TypeVehiculeService {

	@Autowired
	private TypeVehiculeRep typeVehiculeRep;

	public void save(TypeVehicule typeVehicule) {
		typeVehiculeRep.save(typeVehicule);

	}

	public List<TypeVehicule> findAll() {
		return typeVehiculeRep.findAll();
	}

	public TypeVehicule findByName(String name) {
		return typeVehiculeRep.findByLibelle(name);
	}

	public TypeVehicule findById(long id) {
		return typeVehiculeRep.findById(id);
	}

	public TypeVehicule findOne(long id) {
		// TODO Auto-generated method stub
		return typeVehiculeRep.findOne(id);
	}

}
