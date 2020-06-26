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

import com.example.demo.dto.RegionDto;
import com.example.demo.services.RegionService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/lieux/region")
public class RegionController {

	@Autowired
	RegionService regionService;
	
	@GetMapping("/find")
	public RegionDto find(@RequestParam(value = "id")Long id) {
		return regionService.findOne(id);
	}
	
	@GetMapping("/findAll")
	public List<RegionDto> findAll() {
		return regionService.findAll();	
	}
	
	@GetMapping("/inRange")
	public List<RegionDto> findAllById(@RequestParam(value = "ids") List<Long> ids) {
		return regionService.findAllById(ids);
	}
	
	@PostMapping("/save")
	public String save(@RequestBody RegionDto dto) {
		return regionService.save(dto);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody String json) {
		return regionService.update(json);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam( value = "id") Long id) {
		return regionService.delete(id);
	}

}
