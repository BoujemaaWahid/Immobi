package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DemandeDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.services.DemandeService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/message")
public class MsgController {

	@Autowired
	private DemandeService demandeService;
	
	@GetMapping("/findAll")
	public List<DemandeDto> findAll(){
		return demandeService.findAll();
	}
	
	@GetMapping("/message/findAll")
	public List<MessageDto> findAllMsg(){
		return demandeService.findAllMsg();
	}
	
	@GetMapping("/findOne")
	public DemandeDto findOne(@RequestParam("id") Long id){
		return demandeService.findOne(id);
	}
	
	@GetMapping("/message/findOne")
	public MessageDto findOneMsg(@RequestParam("id") Long id){
		return demandeService.findOneMsg(id);
	}
	
	@PostMapping("/demande/save")
	public String save(@RequestBody DemandeDto dto){
		return demandeService.save(dto);
	}
	
	@PostMapping("/message/save")
	public String saveMsg(@RequestBody MessageDto dto){
		return demandeService.saveMsg(dto);
	}
	
	@DeleteMapping("/demande/delete")
	public String delete(@RequestParam("id") Long id) {
		return demandeService.delete(id);
	}
	
	@DeleteMapping("/message/delete")
	public String deleteMsg(@RequestParam("id") Long id) {
		return demandeService.deleteMsg(id);
	}
}
