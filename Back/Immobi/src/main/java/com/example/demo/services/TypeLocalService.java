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

@Service
public class TypeLocalService {
	@Autowired
	TypeRepository typeRepository;
	
	@Autowired
	CacheComponent componentServ;
	
	@Autowired
	ModelMapper modelMapper;

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
	
	public String delete(Long id) {
		try {
			typeRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}

}
