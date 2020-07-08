package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DemandeDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.entitys.Demande;
import com.example.demo.entitys.Message;
import com.example.demo.repositorys.DemandeRepository;
import com.example.demo.repositorys.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DemandeService {

	@Autowired
	private DemandeRepository demandeRepo;
	
	@Autowired MessageRepository messageRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@SuppressWarnings("unused")
	@Autowired
	private ObjectMapper objectMapper;

	public List<DemandeDto> findAll(){
		List<DemandeDto> demandes = new ArrayList<DemandeDto>();
		demandeRepo.findAll().forEach(item->{
			DemandeDto dto = new DemandeDto();
			modelMapper.map(item, dto);
			demandes.add(dto);
		});
		return demandes;
	}
	
	public List<MessageDto> findAllMsg(){
		List<MessageDto> msg = new ArrayList<MessageDto>();
		messageRepo.findAll().forEach(item->{
			MessageDto dto = new MessageDto();
			modelMapper.map(item, dto);
			msg.add(dto);
		});
		return msg;
	}
	
	public DemandeDto findOne(Long id){
		Demande entity = demandeRepo.findById(id).get();
		DemandeDto dto = new DemandeDto();
		if( entity != null )
			modelMapper.map(entity, dto);
		return dto;
	}
	
	public MessageDto findOneMsg(Long id){
		Message entity = messageRepo.findById(id).get();
		MessageDto dto = new MessageDto();
		if( entity != null )
			modelMapper.map(entity, dto);
		return dto;
	}
	public String save(DemandeDto dto){
		Demande demande = new Demande();
		try {
			modelMapper.map(dto, demande);
			demandeRepo.save(demande);
			return "200";
		}catch(Exception ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
	public String saveMsg(MessageDto dto){
		Message msg = new Message();
		try {
			modelMapper.map(dto, msg);
			messageRepo.save(msg);
			return "200";
		}catch(Exception ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
	public String deleteMsg(Long id){
		messageRepo.deleteById(id);
		return "200";
	}
	
	public String delete(Long id){
		demandeRepo.deleteById(id);
		return "200";
	}
}
