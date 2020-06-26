package com.example.demo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Local;

@Repository
public interface LocalRepository extends CrudRepository<Local, Long>{

	@Query(
	value = "select l from Local l where l.prix >= :min_budget and l.prix <= :max_budget")
	List<Local> findWithPrice(@Param("min_budget")double min_budget, @Param("max_budget")double max_budget);
	
}
