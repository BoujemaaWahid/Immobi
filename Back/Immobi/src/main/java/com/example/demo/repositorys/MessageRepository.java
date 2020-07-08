package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitys.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
