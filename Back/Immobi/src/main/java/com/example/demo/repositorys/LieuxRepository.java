package com.example.demo.repositorys;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entitys.Lieux;

@Repository
public interface LieuxRepository extends CrudRepository<Lieux, Long>{
	
	@Query( 
	value = "select id, label, 'lieux' as flag from lieux union select id, label, 'region' as flag from region",
	nativeQuery = true)
	List<Result> listLieuxRegion();
	
	public static interface Result{
		public Long getId();
		public String getLabel();
		public String getFlag();
	}
}
