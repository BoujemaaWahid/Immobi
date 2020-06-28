package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.TypeLocal;

@Repository
public interface TypeRepository extends JpaRepository<TypeLocal, Long>{

}
