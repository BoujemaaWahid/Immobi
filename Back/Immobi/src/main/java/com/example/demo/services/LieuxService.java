package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LieuxDto;
import com.example.demo.entitys.Lieux;
import com.example.demo.repositorys.LieuxRepository;

@Service
public class LieuxService {
	@Autowired
	private LieuxRepository lieuxRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<LieuxDto> findAll(){
		List<LieuxDto> list = new ArrayList<>();
		lieuxRepository.findAll().forEach(item -> {
			LieuxDto ld = new LieuxDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	public LieuxDto findOne(Long id) {
		try {
			LieuxDto dto = new LieuxDto();
			Optional<Lieux> entity = lieuxRepository.findById(id);
			modelMapper.map(entity.get(), dto) ;
			return dto;
		}catch(Exception ex) { return new LieuxDto(); }
	}
	
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
		return "200";
	}
	
	public String delete(Long id) {
		try {
			lieuxRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		return "200";
	}
}
