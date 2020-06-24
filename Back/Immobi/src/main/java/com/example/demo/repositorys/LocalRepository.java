package com.example.demo.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Local;

@Repository
public interface LocalRepository extends CrudRepository<Local, Long>{

}
