package com.example.demo.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Region;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long>{

}
