package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.CacheComponent;
import com.example.demo.dto.TypeLocalDto;
import com.example.demo.entitys.TypeLocal;
import com.example.demo.repositorys.TypeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class TypeLocalService {
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private CacheComponent componentServ;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Cacheable("types_all")
	public List<TypeLocalDto> findAll(){
		List<TypeLocalDto> list = new ArrayList<>();
		typeRepository.findAll().forEach(item -> {
			TypeLocalDto ld = new TypeLocalDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	@Cacheable("type_one")
	public TypeLocalDto findOne(Long id) {
		try {
			TypeLocalDto dto = new TypeLocalDto();
			Optional<TypeLocal> entity = typeRepository.findById(id);
			modelMapper.map(entity.get(), dto) ;
			return dto;
		}catch(Exception ex) { return new TypeLocalDto(); }
	}
	
	@Cacheable("types_all_ids")
	public List<TypeLocalDto> findAllById(List<Long> ids){
		List<TypeLocalDto> list = new ArrayList<>();
		typeRepository.findAllById(ids).forEach(item -> {
			TypeLocalDto ld = new TypeLocalDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	public String save(TypeLocalDto dto) {
		try {
			TypeLocal entity = new TypeLocal();
			modelMapper.map(dto, entity);
			typeRepository.save(entity);
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
			TypeLocalDto dtoNew = new TypeLocalDto();
			Optional<TypeLocal> entity = typeRepository.findById(id);
			modelMapper.map(entity.get(), dtoNew);
			JsonNode entityJson = objectMapper.readTree(objectMapper.writeValueAsString(dtoNew));
			data.fieldNames().forEachRemaining(field->{
				if( !data.get(field).isNull()  ) {
					((ObjectNode)entityJson).put(field, data.get(field));
				}
			});
			TypeLocalDto toSave = objectMapper.convertValue(entityJson, TypeLocalDto.class);
			TypeLocal finalEntity = new TypeLocal();
			modelMapper.map(toSave, finalEntity);
			typeRepository.save(finalEntity);
		}catch(Exception ex) {return ex.getMessage();}
		componentServ.evictAllCaches();
		return "200";
	}
	
	public String delete(Long id) {
		try {
			typeRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}

}
