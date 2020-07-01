package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entitys.User;
import com.example.demo.extradtos.LoginDto;
import com.example.demo.repositorys.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public boolean save(UserDto dto) {
		try {
			User entity = new User();
			modelMapper.map(dto, entity);
			userRepository.save(entity);
		}catch(Exception ex) { return false; }
		return true;
	}
	public boolean isThere(int type, String value) {
		User user = null;
		switch(type) {
			case 1: user = userRepository.findByEmail(value); break;
			case 2: user = userRepository.findByUsername(value); break;
			case 3: user = userRepository.findByPhone(value); break;
		}
		if( user == null ) return false;
		return true;
	}
	public List<UserDto> findAll() {
		List<UserDto> users = new ArrayList<UserDto>();
		userRepository.findAll().forEach(item->{
			UserDto dto = new UserDto();
			modelMapper.map(item, dto);
			users.add(dto);
		});
		return users;
	}
	public UserDto findById(Long id) {
		User user = userRepository.findById(id).get();
		UserDto dto = new UserDto();
		if (user != null ) {
			modelMapper.map(user, dto);
		}
		return dto;
	}
	public LoginDto login(LoginDto dto) {
		User user = null;
		switch( dto.idType() ) {
			case PHONE: user = userRepository.findByPhone(dto.getIdentifiant()); break;
			case EMAIL: user = userRepository.findByEmail(dto.getIdentifiant()); break;
			case USERNAME: user = userRepository.findByUsername(dto.getIdentifiant()); break;
		}
		LoginDto login = new LoginDto();
		if( user != null ) {
			login.setIdentifiant(  Long.toString( user.getId() )  );
			login.setPassword( user.getPassword() );
			login.setAdmin( user.isAdmin() );
		}
		return login;
	}
	public UserDto findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		UserDto ud = new UserDto();
		if( user != null )
			modelMapper.map(user,  ud);
		return ud;
	}
}
