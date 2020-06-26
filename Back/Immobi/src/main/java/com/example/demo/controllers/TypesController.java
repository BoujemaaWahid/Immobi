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
import com.example.demo.dto.TypeLocalDto;
import com.example.demo.services.TypeLocalService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/types")
public class TypesController {
	@Autowired
	TypeLocalService typeLocalService;


	@GetMapping("/find")
	public TypeLocalDto find(@RequestParam(value = "id")Long id) {
		return typeLocalService.findOne(id);
	}
	
	@GetMapping("/findAll")
	public List<TypeLocalDto> findAll() {
		return typeLocalService.findAll();	
	}
	
	@GetMapping("/inRange")
	public List<TypeLocalDto> findAllById(@RequestParam(value = "ids") List<Long> ids) {
		return typeLocalService.findAllById(ids);
	}
	
	@PostMapping("/save")
	public String save(@RequestBody TypeLocalDto dto) {
		return typeLocalService.save(dto);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody TypeLocalDto dto) {
		return "400";
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam( value = "id") Long id) {
		System.out.println("ID" + id);
		return typeLocalService.delete(id);
	}

	
	
}
