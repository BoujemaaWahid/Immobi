package com.example.demo.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
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

import com.example.demo.dto.LieuxDto;
import com.example.demo.services.LieuxService;


import com.example.demo.services.AdressesService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/lieux")
public class LieuxController {
	@Autowired
	LieuxService lieuxService;
	
	@Autowired
	AdressesService adressesService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/find")
	public LieuxDto find(@RequestParam(value = "id")Long id) {
		return lieuxService.findOne(id);
	}
	
	@GetMapping("/findAll")
	public List<LieuxDto> findAll() {
		return lieuxService.findAll();	
	}
	
	@GetMapping("/inRange")
	public List<LieuxDto> findAllById(@RequestParam(value = "ids") List<Long> ids) {
		return lieuxService.findAllById(ids);
	}
	
	@PostMapping("/save")
	public String save(@RequestBody LieuxDto dto) {
		return lieuxService.save(dto);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody String json) {
		return lieuxService.update(json);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam( value = "id") Long id) {
		return lieuxService.delete(id);
	}

}
