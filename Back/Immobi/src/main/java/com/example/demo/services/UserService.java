package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.CacheComponent;
import com.example.demo.dto.UserDto;
import com.example.demo.entitys.Local;
import com.example.demo.entitys.User;
import com.example.demo.extradtos.LoginDto;
import com.example.demo.repositorys.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private CacheComponent componentServ;
	
	
	public boolean save(UserDto dto) {
		try {
			User entity = new User();
			modelMapper.map(dto, entity);
			userRepository.save(entity);
		}catch(Exception ex) { return false; }
		componentServ.evictAllCaches();
		return true;
	}
	public boolean saveFav(Long idu, Long idl) {
		try {
			Local l = new Local();
			l.setId(idl);
			User u = userRepository.findById(idu).get();
			u.getFavoires().add(l);
			userRepository.save(u);
		}catch(Exception ex) {}
		return false;
	}
	
	public UserDto getFavs(Long idu) {
		User u = userRepository.findById(idu).get();
		u.setPassword(null);
		u.setEmail(null);
		u.setTelephone(null);
		UserDto ud = new UserDto();
		modelMapper.map(u, ud);
		componentServ.evictAllCaches();
		return ud;
	}
	public boolean removeFav(Long idu, Long idl) {
		try {
			Local l = new Local();
			l.setId(idl);
			User u = userRepository.findById(idu).get();
			List<Local> nl = u.getFavoires().stream().filter(local-> local.getId() != idl).collect(Collectors.toList());
			u.setFavoires(nl);
			userRepository.save(u);
			componentServ.evictAllCaches();
			return true;
		}catch(Exception ex) {}
		return false;
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
