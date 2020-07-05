package com.example.demo.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.CacheComponent;
import com.example.demo.dto.LocalDto;
import com.example.demo.entitys.Local;
import com.example.demo.repositorys.AdressesRepository;
import com.example.demo.repositorys.LieuxRepository;
import com.example.demo.repositorys.LocalCustom;
import com.example.demo.repositorys.LocalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class LocalService {

	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CacheComponent componentServ;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	LieuxRepository lieuxRepository;
	
	@Autowired
	AdressesRepository adressesRepository;
	
	public List<LocalDto> getByFilters(String jsonFilters){
		List<LocalDto> list = new ArrayList<>();
		List<Long> types = null;
		Integer pieces = null;
		Integer chambres = null;
		List<Long> adresses = null;
		Date[] datesRange = {null, null};
		Double[] surface = {null, null, null, null};
		Double[] prix = {null, null};
		boolean disponible = true;
		Boolean[] projet = {null, null};
		try {
			JsonNode data = objectMapper.readTree(jsonFilters);
			try { 
				JsonNode dates = data.get("date");
				String []pmin = dates.get("min").asText().split("-");
				String []pmax = dates.get("max").asText().split("-");
				LocalDate dmin = LocalDate.of(Integer.valueOf(pmin[0]), Integer.valueOf(pmin[1]), Integer.valueOf(pmin[2]));
				LocalDate dmax = LocalDate.of(Integer.valueOf(pmax[0]), Integer.valueOf(pmax[1]), Integer.valueOf(pmax[2]));
				datesRange[0] =  Date.from(dmin.atStartOfDay(ZoneId.systemDefault()).toInstant());
				datesRange[1] =  Date.from(dmax.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}catch(Exception ex) {}
			try { 
				JsonNode dates = data.get("surface");
				surface[0] = dates.get("min").asDouble();
				surface[1] = dates.get("max").asDouble();
			}catch(Exception ex) {}
			try { 
				JsonNode dates = data.get("terrain");
				surface[2] = dates.get("min").asDouble();
				surface[3] = dates.get("max").asDouble();
			}catch(Exception ex) {}
			try { 
				JsonNode dates = data.get("prix");
				prix[0] = dates.get("min").asDouble();
				prix[1] = dates.get("max").asDouble();
			}catch(Exception ex) {}
			try { 
				JsonNode ts = data.get("types");
				types = new ArrayList<>();
				for(JsonNode e: ts) {
					types.add(e.asLong());
				}
			}catch(Exception ex) {}
			
			try {
				List<Long>lieux, regions;
				lieux = new ArrayList<>();
				regions = new ArrayList<Long>();
				JsonNode data_adresses = data.get("adresses");
				for(JsonNode item: data_adresses) {
					boolean isRegion = item.get("from").asText().equals("region");
					if( !isRegion ) {
						lieux.add(item.get("id").asLong());
					}else {
						regions.add(item.get("id").asLong());
					}
				}
				if( !regions.isEmpty() ) {
					List<Long>lieux_ids = lieuxRepository.getIdsByRegion(regions);
					lieux.addAll(lieux_ids);
					
				}
				if(!lieux.isEmpty()) {
					adresses = adressesRepository.byLieux(lieux);
				}
			}catch(Exception ex) {}
			try { disponible = data.get("disponible").asBoolean(); }catch(Exception ex) {}
			try { pieces = data.get("pieces").asInt(); }catch(Exception ex) {}
			try { chambres = data.get("chambres").asInt(); }catch(Exception ex) {}
			
			try { 
				JsonNode p = data.get("projet");
				projet[0] = p.get(0).asBoolean();
				projet[1] = p.get(1).asBoolean();
			}catch(Exception ex) {}
		}catch(Exception ex) {}
		

		
		Specification<Local> spec = Specification
				.where(LocalCustom.disponible(disponible))
				.and(LocalCustom.dateRange(datesRange[0], datesRange[1]))
				.and(LocalCustom.surfaceRange(surface[0], surface[1]))
				.and(LocalCustom.surfaceTerrainRange(surface[2], surface[3]))
				.and(LocalCustom.isAchat(projet[0], projet[1]))
				.and(LocalCustom.prixRange(prix[0], prix[1]))
				.and(LocalCustom.adresses(adresses))
				.and(LocalCustom.pieces(pieces))
				.and(LocalCustom.chambres(chambres))
				.and(LocalCustom.types(types));
		
		
		List<Local> locales = localRepository.findAll(spec);
		locales.forEach(item -> {
			LocalDto ld = new LocalDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	@Cacheable("local_all")
	public List<LocalDto> findAll(){
		List<LocalDto> list = new ArrayList<>();
		localRepository.findAll().forEach(item -> {
			LocalDto ld = new LocalDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	@Cacheable("local_one")
	public LocalDto findOne(Long id) {
		try {
			LocalDto dto = new LocalDto();
			Optional<Local> entity = localRepository.findById(id);
			modelMapper.map(entity.get(), dto) ;
			return dto;
		}catch(Exception ex) { return new LocalDto(); }
	}
	
	@Cacheable("local_all_ids")
	public List<LocalDto> findAllById(List<Long> ids){
		List<LocalDto> list = new ArrayList<>();
		localRepository.findAllById(ids).forEach(item -> {
			LocalDto ld = new LocalDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	public Long save(LocalDto dto) {
		Long id = (long) 0;
		try {
			Local entity = new Local();
			modelMapper.map(dto, entity);
			id = localRepository.saveAndFlush(entity).getId();
		}catch(Exception ex) { }
		componentServ.evictAllCaches();
		return id;
	}
	
	@SuppressWarnings("deprecation")
	public String update(String json) {
		try {
			JsonNode data = objectMapper.readTree(json);
			Long id = data.get("id").asLong();
			if ( data.get("id").isNull() ) return "UPDATE: ID NOT FOUND EXEPTION";
			LocalDto dtoNew = new LocalDto();
			Optional<Local> entity = localRepository.findById(id);
			modelMapper.map(entity.get(), dtoNew);
			JsonNode entityJson = objectMapper.readTree(objectMapper.writeValueAsString(dtoNew));
			data.fieldNames().forEachRemaining(field->{
				if( !data.get(field).isNull()  ) {
					((ObjectNode)entityJson).put(field, data.get(field));
				}
			});
			LocalDto toSave = objectMapper.convertValue(entityJson, LocalDto.class);
			Local finalEntity = new Local();
			modelMapper.map(toSave, finalEntity);
			localRepository.save(finalEntity);
		}catch(Exception ex) {return ex.getMessage();}
		componentServ.evictAllCaches();
		return "200";
	}
	
	public String delete(Long id) {
		try {
			localRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}
}
