package com.example.demo.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long>{

}
