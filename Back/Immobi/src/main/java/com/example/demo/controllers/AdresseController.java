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

import com.example.demo.dto.AdresseDto;
import com.example.demo.services.AdressesService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/adresses")
public class AdresseController {

	@Autowired
	AdressesService adresseServices;

	@GetMapping("/find")
	public AdresseDto find(@RequestParam(value = "id")Long id) {
		return adresseServices.findOne(id);
	}
	
	@GetMapping("/findByLieu")
	public List<AdresseDto> findByLieu(@RequestParam(value = "id")Long id) {
		return adresseServices.byLieuId(id);
	}
	
	@GetMapping("/findAll")
	public List<AdresseDto> findAll() {
		return adresseServices.findAll();
	}
	
	@GetMapping("/inRange")
	public List<AdresseDto> findAllById(@RequestParam(value = "ids") List<Long> ids) {
		return adresseServices.findAllById(ids);
	}
	
	@PostMapping("/save")
	public String save(@RequestBody AdresseDto dto) {
		return adresseServices.save(dto);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody AdresseDto dto) {
		return adresseServices.save(dto);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam( value = "id") Long id) {
		return adresseServices.delete(id);
	}

}
