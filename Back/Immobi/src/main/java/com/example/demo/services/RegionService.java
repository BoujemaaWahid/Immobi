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

@Service
public class RegionService {
	@Autowired
	RegionRepository regionRepository;
	

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CacheComponent componentServ;
	
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
	
	public String delete(Long id) {
		try {
			regionRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}

}
