package com.example.demo.repositorys;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;
import com.example.demo.entitys.Local;
import com.example.demo.entitys.TypeLocal;

public class LocalCustom {	
	
	public static Specification<Local> dateRange(Date date_min, Date date_max){
		if( date_max == null || date_min == null ) return null;
		return (root, query, cb) ->{ 
	        return cb.between(root.get("date_publication"), date_min, date_max);
		};
	}
	
	public static Specification<Local> surfaceRange(Double min, Double max){
		if( min == null || max == null ) return null;
		return (root, query, cb) ->{ 
	        return cb.between(root.get("surface"), min, max);
		};
	}
	public static Specification<Local> surfaceTerrainRange(Double min, Double max){
		if( min == null || max == null ) return null;
		return (root, query, cb) ->{ 
	        return cb.between(root.get("surface_terrain"), min, max);
		};
	}
	
	public static Specification<Local> prixRange(Double min, Double max){
		if( min == null || max == null ) return null;
		return (root, query, cb) ->{ 
	        return cb.between(root.get("prix"), min, max);
		};
	}
	
	public static Specification<Local> pieces(Integer nombre){
		if( nombre == null || nombre == 0 ) return null;
		if( nombre < 5 ) {
			return (root, query, cb) ->{ 
		        return cb.equal(root.get("num_pieces"), nombre);
			};
		}  
		return (root, query, cb) ->{ 
	        return cb.greaterThanOrEqualTo(root.get("num_pieces"), nombre);
		};
	}
	
	public static Specification<Local> chambres(Integer nombre){
		if( nombre == null || nombre == 0 ) return null;
		if( nombre < 5 ) {
			return (root, query, cb) ->{ 
		        return cb.equal(root.get("num_chambres"), nombre);
			};
		}  
		return (root, query, cb) ->{ 
	        return cb.greaterThanOrEqualTo(root.get("num_chambres"), nombre);
		};
	}
	
	public static Specification<Local> types(List<Long> list){
		return (root, query, cb) ->{ 
			if( list == null || list.isEmpty() )return null;
			
			Join<Local, TypeLocal> jlt = root.join("types");
			query.distinct(true);
			return jlt.in( list );
		};
	}
	
	public static Specification<Local> adresses(List<Long> adresses){
		return (root, query, cb) -> {
	            if( adresses == null || adresses.isEmpty() )return null;
	            return root.join("adresse").in( adresses );
		};
	}
	
	public static Specification<Local> disponible(boolean disponible){
		return (root, query, cb) ->{ 
	        return cb.equal(root.get("disponible"), disponible);
		};
	}
	public static Specification<Local> isAchat(Boolean achat, Boolean louer){
		return (root, query, cb) ->{
			if( achat == null && louer == null) return null;
			if( achat && !louer)
				return cb.equal(root.get("projet"), true);
			else if( !achat && louer )
				return cb.equal(root.get("projet"), false);
			else return null;		
		};
	}
	
}
