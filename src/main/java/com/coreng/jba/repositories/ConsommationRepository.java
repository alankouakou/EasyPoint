package com.coreng.jba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coreng.jba.entities.Consommation;
import com.coreng.jba.entities.TypeConso;

public interface ConsommationRepository extends JpaRepository<Consommation, Long> {

	List<TypeConso> findByTypeConso(TypeConso typeConso);

}
