package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.ImageDto;
import com.example.demo.services.ImageService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/local/images")
public class ImageController {
	@Autowired
	ImageService imageService;
	
	
	@GetMapping("/find")
	public ImageDto find(@RequestParam(value = "id")Long id) {
		return imageService.findOne(id);
	}
	
	@GetMapping("/findAll")
	public List<ImageDto> findAll() {
		return imageService.findAll();	
	}
	
	@GetMapping("/inRange")
	public List<ImageDto> findAllById(@RequestParam(value = "ids") List<Long> ids) {
		return imageService.findAllById(ids);
	}
	
	@PostMapping("/save")
	public String save(@RequestBody ImageDto dto) {
		return imageService.save(dto);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody ImageDto dto) {
		return "400";
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam( value = "id") Long id) {
		return imageService.delete(id);
	}
}
