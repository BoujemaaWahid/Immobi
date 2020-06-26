package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.CacheComponent;
import com.example.demo.dto.AdresseDto;
import com.example.demo.entitys.Adresse;
import com.example.demo.repositorys.AdressesRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Service
public class AdressesService {
	@Autowired
	private AdressesRepository adressesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CacheComponent componentServ;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Cacheable("adresses_all")
	public List<AdresseDto> findAll(){
		List<AdresseDto> list = new ArrayList<>();
		adressesRepository.findAll().forEach(item -> {
			AdresseDto ld = new AdresseDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	@Cacheable("adresse_one")
	public AdresseDto findOne(Long id) {
		try {
			AdresseDto dto = new AdresseDto();
			Optional<Adresse> entity = adressesRepository.findById(id);
			modelMapper.map(entity.get(), dto);
			return dto;
		}catch(Exception ex) { return new AdresseDto(); }
	}
	
	@Cacheable("adreses_all_ids")
	public List<AdresseDto> findAllById(List<Long> ids){
		List<AdresseDto> list = new ArrayList<>();
		adressesRepository.findAllById(ids).forEach(item -> {
			AdresseDto ld = new AdresseDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	public String save(AdresseDto dto) {
		try {
			Adresse entity = new Adresse();
			modelMapper.map(dto, entity);
			adressesRepository.save(entity);
		}catch(Exception ex) { return ex.getMessage(); }
		componentServ.evictAllCaches();
		return "200";
	}

	@SuppressWarnings("deprecation")
	public String update(String json) {
		try {
			JsonNode data = objectMapper.readTree(json);
			Long id = data.get("id").asLong();
			if ( data.get("id").isNull() ) return "UPDATE: ID NOT FOUND EXEPTION";
			AdresseDto dtoNew = new AdresseDto();
			Optional<Adresse> entity = adressesRepository.findById(id);
			modelMapper.map(entity.get(), dtoNew);
			JsonNode entityJson = objectMapper.readTree(objectMapper.writeValueAsString(dtoNew));
			data.fieldNames().forEachRemaining(field->{
				if( !data.get(field).isNull()  ) {
					((ObjectNode)entityJson).put(field, data.get(field));
				}
			});
			AdresseDto toSave = objectMapper.convertValue(entityJson, AdresseDto.class);
			Adresse finalEntity = new Adresse();
			modelMapper.map(toSave, finalEntity);
			adressesRepository.save(finalEntity);
		}catch(Exception ex) {return ex.getMessage();}
		componentServ.evictAllCaches();
		return "200";
	}
	
	public String delete(Long id) {
		try {
			adressesRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		componentServ.evictAllCaches();
		
		return "200";
	}
	
	@Cacheable("adresses_lieux_id")
	public List<AdresseDto> byLieuId(Long id) {
		List<AdresseDto> dtos = new ArrayList<>();
		try {
			List<Adresse> adresses = adressesRepository.byLieu(id);
			adresses.forEach(e -> {
				AdresseDto dt = new AdresseDto();
				modelMapper.map(e, dt);
				dtos.add(dt);
			});
		}catch(Exception ex) { }
		return dtos;
	}
	
}
