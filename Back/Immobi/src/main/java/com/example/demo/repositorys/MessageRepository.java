package com.example.demo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
	@Query("select m from Message m")
	List<Message> getAll();
}
