package com.example.demo.repositorys;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entitys.Lieux;

@Repository
public interface LieuxRepository extends JpaRepository<Lieux, Long>{
	
	@Query( 
	value = "select id, label, 'lieux' as flag from lieux "
			+ "union select id, label, 'region' as flag from region order by label asc",
	nativeQuery = true)
	List<Result> listLieuxRegion();
	
	@Query(value = "select distinct(id) from lieux where region in :list", nativeQuery = true)
	List<Long> getIdsByRegion(@Param("list") List<Long> regions);
	
	public static interface Result{
		public Long getId();
		public String getLabel();
		public String getFlag();
	}
}
