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
import com.example.demo.dto.LocalDto;
import com.example.demo.services.LocalService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/local")
public class LocalController {

	@Autowired
	LocalService localService;
	
	
	@GetMapping("/find")
	public LocalDto find(@RequestParam(value = "id")Long id) {
		return localService.findOne(id);
	}
	
	@GetMapping("/findAll")
	public List<LocalDto> findAll() {
		return localService.findAll();	
	}
	
	@GetMapping("/inRange")
	public List<LocalDto> findAllById(@RequestParam(value = "ids") List<Long> ids) {
		return localService.findAllById(ids);
	}
	
	@GetMapping("/filters")
	public List<LocalDto> X(@RequestParam(value = "data") String json) {
		return localService.getByFilters(json);
	}
	
	@PostMapping("/save")
	public String save(@RequestBody LocalDto dto) {
		return localService.save(dto);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody String json) {
		return localService.update(json);
	}
	
	@DeleteMapping("/delete")
	public String delete(@RequestParam( value = "id") Long id) {
		return localService.delete(id);
	}

}
