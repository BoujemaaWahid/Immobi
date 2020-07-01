package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entitys.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "select u from User u where u.username = :username")
	User findByUsername(@Param("username") String username);
	
	@Query(value = "select u from User u where u.email = :email")
	User findByEmail(@Param("email") String email);
	
	@Query(value = "select u from User u where u.telephone = :telephone")
	User findByPhone(@Param("telephone") String phone);
	
	@Query(value = "select u from User u where u.admin = true")
	User findAdmin();
}
