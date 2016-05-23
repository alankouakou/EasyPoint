package com.coreng.jba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreng.jba.entities.Prestation;
import com.coreng.jba.entities.TypeConso;

public interface TypeConsoRepository extends JpaRepository<TypeConso, Long> {
	List<TypeConso> findByPrestation(Prestation prestation);

}
