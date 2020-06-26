package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.CacheComponent;
import com.example.demo.dto.ImageDto;
import com.example.demo.entitys.Image;
import com.example.demo.repositorys.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CacheComponent componentServ;
	
	@Cacheable("images_all")
	public List<ImageDto> findAll(){
		List<ImageDto> list = new ArrayList<>();
		imageRepository.findAll().forEach(item -> {
			ImageDto ld = new ImageDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	@Cacheable("image_one")
	public ImageDto findOne(Long id) {
		try {
			ImageDto dto = new ImageDto();
			Optional<Image> entity = imageRepository.findById(id);
			modelMapper.map(entity.get(), dto) ;
			return dto;
		}catch(Exception ex) { return new ImageDto(); }
	}
	
	@Cacheable("images_all_ids")
	public List<ImageDto> findAllById(List<Long> ids){
		List<ImageDto> list = new ArrayList<>();
		imageRepository.findAllById(ids).forEach(item -> {
			ImageDto ld = new ImageDto();
			modelMapper.map(item, ld);
			list.add(ld);
		});
		return list;
	}
	
	public String save(ImageDto dto) {
		try {
			Image entity = new Image();
			modelMapper.map(dto, entity);
			imageRepository.save(entity);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}
	
	public String delete(Long id) {
		try {
			imageRepository.deleteById(id);
		}catch(Exception ex) { return ex.getMessage(); }
		
		componentServ.evictAllCaches();
		return "200";
	}
}
