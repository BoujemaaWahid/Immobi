package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.CacheComponent;
import com.example.demo.dto.RegionDto;
import com.example.demo.entitys.Region;
import com.example.demo.repositorys.RegionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CacheComponent componentServ;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Cacheable("region_all")
	public List<RegionDto> findAll(){
		List<RegionDto> list = new ArrayList<>();
		regionRepository.findAll().forEach(item -> {
			RegionDto ld = new RegionDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	@Cacheable("region_one")
	public RegionDto findOne(Long id) {
		try {
			RegionDto dto = new RegionDto();
			Optional<Region> entity = regionRepository.findById(id);
			modelMapper.map(entity.get(), dto) ;
			return dto;
		}catch(Exception ex) { return new RegionDto(); }
	}
	
	@Cacheable("region_all_ids")
	public List<RegionDto> findAllById(List<Long> ids){
		List<RegionDto> list = new ArrayList<>();
		regionRepository.findAllById(ids).forEach(item -> {
			RegionDto ld = new RegionDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	public String save(RegionDto dto) {
		try {
			Region entity = new Region();
			modelMapper.map(dto, entity);
			regionRepository.save(entity);
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
			RegionDto dtoNew = new RegionDto();
			Optional<Region> entity = regionRepository.findById(id);
			modelMapper.map(entity.get(), dtoNew);
			JsonNode entityJson = objectMapper.readTree(objectMapper.writeValueAsString(dtoNew));
			data.fieldNames().forEachRemaining(field->{
				if( !data.get(field).isNull()  ) {
					((ObjectNode)entityJson).put(field, data.get(field));
				}
			});
			RegionDto toSave = objectMapper.convertValue(entityJson, RegionDto.class);
			Region finalEntity = new Region();
			modelMapper.map(toSave, finalEntity);
			regionRepository.save(finalEntity);
		}catch(Exception ex) {return ex.getMessage();}
		componentServ.evictAllCaches();
		return "200";
	}
	public String delete(Long id) {
		try {
			regionRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}

}
