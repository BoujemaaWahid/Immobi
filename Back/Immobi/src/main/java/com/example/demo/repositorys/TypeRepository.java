package com.example.demo.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.TypeLocal;

@Repository
public interface TypeRepository extends CrudRepository<TypeLocal, Long>{

}
