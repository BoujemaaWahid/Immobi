package com.example.demo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Adresse;

@Repository
public interface AdressesRepository extends CrudRepository<Adresse, Long> {
	@Query(
			value = "select * from adresses where lieu_id = ?1",
			nativeQuery = true
			)
	List<Adresse> byLieu(Long id);
}
