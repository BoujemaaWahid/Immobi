package com.example.demo.repositorys;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entitys.Lieux;

@Repository
public interface LieuxRepository extends CrudRepository<Lieux, Long>{
	
}
