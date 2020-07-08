package com.example.demo.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.entitys.Message;

@NoRepositoryBean
public interface InheritRepo<T extends Message> extends JpaRepository<T, Long>{

}
