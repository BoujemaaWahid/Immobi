package com.example.demo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Adresse;

@Repository
public interface AdressesRepository extends JpaRepository<Adresse, Long> {
	@Query(
			value = "select * from adresses where lieu_id = :id",
			nativeQuery = true
			)
	List<Adresse> byLieu(@Param("id") Long id);
	
	@Query(value = "select id from adresses where lieu_id in :list", nativeQuery = true)
	List<Long> byLieux(@Param("list") List<Long> ids);
}
