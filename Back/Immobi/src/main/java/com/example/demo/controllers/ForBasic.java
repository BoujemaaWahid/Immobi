package com.example.demo.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositorys.LieuxRepository;
import com.example.demo.services.LieuxService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ForBasic {

	@Autowired
	LieuxService lieux;
	
	@Autowired
	LieuxRepository rep;
	
	@Autowired
	ModelMapper modelMapper;
	
	@RequestMapping("/")
	public String basicAuth() {
		return "<h3>Immobi Spring</h3>";
	}
	
	@PostMapping("/t")
	public String s(@RequestParam("data") String data) {
		System.out.println("10");
		return "POST DONE "+data;
	}
	
	@GetMapping("/basicFilter")
	public String getBasicData(@RequestParam("data") String data) {
		System.out.println("ok");
		return "spring " + data;
	}
	
}
