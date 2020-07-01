package com.example.demo.controllers;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmailSender;
import com.example.demo.dto.UserDto;
import com.example.demo.extradtos.LoginDto;
import com.example.demo.services.UserService;



@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	EmailSender emailSender;
	
	
	@GetMapping("/validation")
	public boolean validation(@RequestParam(value = "email")String email) throws MessagingException {
		
		emailSender.sendConfirmation(email);
		return true;
	}


	
	@GetMapping("/save")
	public boolean test2() {
		return true;
	}
	
	@GetMapping("/test")
	public boolean test() {
		UserDetails ud = inMemoryUserDetailsManager.loadUserByUsername("admin");
		ud = inMemoryUserDetailsManager.updatePassword(ud, new BCryptPasswordEncoder().encode("wahid"));
		inMemoryUserDetailsManager.updateUser(ud);
		return true;
	}
	
	@PostMapping("/login")
	public LoginDto login(@RequestBody LoginDto dto) {
		System.out.println(dto);
		return userService.login(dto);
	}
	
	@PostMapping("/register")
	public Long register(@RequestBody UserDto dto) {
		userService.save(dto);
		return userService.findByEmail(dto.getEmail()).getId();
	}
	
	@GetMapping("/isThere")
	public boolean isThere(@RequestParam("type") Integer type, @RequestParam("value")String value) {
		return userService.isThere(type, value);
	}
	
}
