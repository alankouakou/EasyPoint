package com.coreng.jba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coreng.jba.entities.TarifAuto;

public interface TarifAutoRepository extends JpaRepository<TarifAuto, Long> {

	@Query("select t from TarifAuto t order by t.name ASC")
	public List<TarifAuto> findAllOrderByName();

	public TarifAuto findByName(String name);

}