package com.example.demo.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EmailSender;
import com.example.demo.dto.DemandeDto;
import com.example.demo.dto.LocalDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.entitys.Local;
import com.example.demo.services.DemandeService;
import com.example.demo.services.LocalService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/message")
public class MsgController {

	@Autowired
	private DemandeService demandeService;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	LocalService localService;
	

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SimpMessagingTemplate template;
	
	
	
	@GetMapping("/findAll")
	public List<DemandeDto> findAll(){
		return demandeService.findAll();
	}
	
	@GetMapping("/mixAll")
	public List<DemandeDto>mixAll(){
		return demandeService.mixAll();
	}
	@GetMapping("/mixAllCount")
	public int mixAllCount(){
		return demandeService.mixAll().stream().filter(item-> !item.isVue()).collect(Collectors.toList()).size();
	}
	@PostMapping("/setSeen")
	public void setSeen(@RequestParam("id")Long id, @RequestParam("type")String type) {
		this.demandeService.setSeen(id, type);
	}
	
	@PostMapping("/sendMessage")
	public void sendMessage(@RequestParam("email")String email, @RequestParam("message")String message, @RequestParam("id")Long id, @RequestParam("type") String type) {
		emailSender.sendMessage(email, message);
		demandeService.setResp(id, type);
	}
	@GetMapping("/msg/findAll")
	public List<MessageDto> findAllMsg(){
		return demandeService.findAllMsg();
	}
	
	@GetMapping("/findOne")
	public DemandeDto findOne(@RequestParam("id") Long id){
		return demandeService.findOne(id);
	}
	
	@GetMapping("/msg/findOne")
	public MessageDto findOneMsg(@RequestParam("id") Long id){
		return demandeService.findOneMsg(id);
	}
	
	@PostMapping("/demande/save")
	public String save(@RequestBody DemandeDto dto){
		Long id = demandeService.save(dto);
		DemandeDto dtos = demandeService.findOne(id);
		Long lid = dtos.getLocal().getId();
		LocalDto dd = localService.findOne(lid);
		dd.setImages( Arrays.asList( dd.getImages().get(0)) );
		Local dil = new Local();
		modelMapper.map(dd, dil);
		dtos.setLocal(dil);
		template.convertAndSend("/rt/demande", dtos);
		return "200";
	}
	
	@PostMapping("/msg/save")
	public String saveMsg(@RequestBody MessageDto dto){
		template.convertAndSend("/rt/demande", demandeService.saveMsg(dto));
		return "200";
	}
	
	@DeleteMapping("/demande/delete")
	public String delete(@RequestParam("id") Long id) {
		return demandeService.delete(id);
	}
	
	@DeleteMapping("/msg/delete")
	public String deleteMsg(@RequestParam("id") Long id) {
		return demandeService.deleteMsg(id);
	}
}
