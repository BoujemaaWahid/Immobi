package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserManag {

	@Autowired
	private UserService userService;
	
	@GetMapping("/findAll")
	public List<UserDto> findAll(){
		return this.userService.findAll();
	}
	@GetMapping("/findId")
	public UserDto byId(@RequestParam("id")Long id) {
		return userService.findById(id);
	}
	@DeleteMapping("/rmFav")
	public boolean rmFav(@RequestParam("idu")Long idu, @RequestParam("idl")Long idl) {
		return userService.removeFav(idu, idl);
	}
	
	@PostMapping("/mkFav")
	public boolean mkFav(@RequestParam("idu")Long idu, @RequestParam("idl")Long idl) {
		return userService.saveFav(idu, idl);
	}
}
