package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.CacheComponent;
import com.example.demo.dto.LocalDto;
import com.example.demo.entitys.Local;
import com.example.demo.repositorys.LocalRepository;

@Service
public class LocalService {
	/*
	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CacheComponent componentServ;
	
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
	
	public String save(LocalDto dto) {
		try {
			Local entity = new Local();
			modelMapper.map(dto, entity);
			localRepository.save(entity);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}
	
	public String delete(Long id) {
		try {
			localRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}*/
}
