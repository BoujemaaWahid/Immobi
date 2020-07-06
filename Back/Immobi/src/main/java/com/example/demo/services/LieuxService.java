package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.CacheComponent;
import com.example.demo.dto.LieuxDto;
import com.example.demo.entitys.Lieux;
import com.example.demo.extradtos.LieuxRegion;
import com.example.demo.repositorys.LieuxRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Service
public class LieuxService {
	@Autowired
	private LieuxRepository lieuxRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CacheComponent componentServ;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Cacheable("lieux_all")
	public List<LieuxDto> findAll(){		
		List<LieuxDto> list = new ArrayList<>();
		lieuxRepository.findAll().forEach(item -> {
			LieuxDto ld = new LieuxDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	@Cacheable("lieux_region")
	public List<LieuxRegion> getLieuxRegion(){
		List<LieuxRegion> list = new ArrayList<>();
		lieuxRepository.listLieuxRegion().forEach(e->{
			LieuxRegion lr = new LieuxRegion(e.getId(), e.getLabel(), e.getFlag());
			list.add(lr);
		});
		return list;
	}
	
	@Cacheable("lieux_one")
	public LieuxDto findOne(Long id) {
		try {
			LieuxDto dto = new LieuxDto();
			Optional<Lieux> entity = lieuxRepository.findById(id);
			modelMapper.map(entity.get(), dto) ;
			return dto;
		}catch(Exception ex) { return new LieuxDto(); }
	}
	
	@Cacheable("lieux_all_ids")
	public List<LieuxDto> findAllById(List<Long> ids){
		List<LieuxDto> list = new ArrayList<>();
		lieuxRepository.findAllById(ids).forEach(item -> {
			LieuxDto ld = new LieuxDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	public String save(LieuxDto dto) {
		try {
			Lieux entity = new Lieux();
			modelMapper.map(dto, entity);
			lieuxRepository.save(entity);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}
	
	@SuppressWarnings("deprecation")
	public String update(String json) {
		try {
			System.out.println(json);
			JsonNode data = objectMapper.readTree(json);
			Long id = data.get("id").asLong();
			if ( data.get("id").isNull() ) return "UPDATE: ID NOT FOUND EXEPTION";
			LieuxDto dtoNew = new LieuxDto();
			Optional<Lieux> entity = lieuxRepository.findById(id);
			modelMapper.map(entity.get(), dtoNew);
			JsonNode entityJson = objectMapper.readTree(objectMapper.writeValueAsString(dtoNew));
			data.fieldNames().forEachRemaining(field->{
				if( !data.get(field).isNull()  ) {
					((ObjectNode)entityJson).put(field, data.get(field));
				}
			});
			LieuxDto toSave = objectMapper.convertValue(entityJson, LieuxDto.class);
			Lieux finalEntity = new Lieux();
			modelMapper.map(toSave, finalEntity);
			lieuxRepository.save(finalEntity);
		}catch(Exception ex) {ex.printStackTrace();return ex.getMessage();}
		componentServ.evictAllCaches();
		return "200";
	}
	
	public String delete(Long id) {
		try {
			lieuxRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}
}
