package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entitys.Demande;
import com.example.demo.entitys.Message;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long>{

}
