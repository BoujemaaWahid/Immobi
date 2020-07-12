package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DemandeDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.entitys.Demande;
import com.example.demo.entitys.Image;
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
			List<Image> image = Arrays.asList( dto.getLocal().getImages().get(0) );
			dto.getLocal().setImages(image);
			demandes.add(dto);
		});
		return demandes;
	}
	
	public List<MessageDto> findAllMsg(){
		List<MessageDto> msg = new ArrayList<MessageDto>();
		
		messageRepo.getAll().forEach(item->{
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
	public Long save(DemandeDto dto){
		Demande demande = new Demande();
		try {
			modelMapper.map(dto, demande);
			Demande dem = demandeRepo.saveAndFlush(demande);
			return dem.getId();
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public MessageDto saveMsg(MessageDto dto){
		Message msg = new Message();
		try {
			modelMapper.map(dto, msg);
			Message message = messageRepo.saveAndFlush(msg);
			MessageDto dtos = new MessageDto();
			modelMapper.map(message, dtos);
			return dtos;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
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
	
	public List<DemandeDto> mixAll(){
		List<DemandeDto> ld = this.findAll();
		List<MessageDto> md = this.findAllMsg();
		md.forEach(item->{
			DemandeDto d = new DemandeDto();
			long id = item.getId();
			String email = item.getEmail();
			String message = item.getMessage();
			d.setId( id );
			d.setEmail( email );
			d.setMessage( message );
			d.setResponded( item.isResponded() );
			ld.add(d);
		});

		List<DemandeDto> list = ld.stream().filter(item-> item.isResponded() == false ).collect(Collectors.toList());
		return list;
	}
	public void setSeen(Long id, String type) {
		switch(type) {
		case "message":{
			Message msg = messageRepo.findById(id).get();
			msg.setVue(true);
			messageRepo.save(msg);
		}break;
		case "demande":{
			Demande dm = demandeRepo.findById(id).get();
			dm.setVue(true);
			demandeRepo.save(dm);
		}break;
		}
	}
	public void setResp(Long id, String type) {
		switch(type) {
		case "message":{
			Message msg = messageRepo.findById(id).get();
			msg.setResponded(true);
			messageRepo.save(msg);
		}break;
		case "demande":{
			Demande dm = demandeRepo.findById(id).get();
			dm.setResponded(true);
			demandeRepo.save(dm);
		}break;
		}
	}
}
