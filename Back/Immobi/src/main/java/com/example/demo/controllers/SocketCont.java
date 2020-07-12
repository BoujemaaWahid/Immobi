package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class SocketCont {
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/message")
	@SendTo("/rt/demande")
	public String ws(String message) {
		System.out.println(message);
		for(int cmp = 0; cmp < 10; cmp++) {
			template.convertAndSend("/rt/2", cmp);
		}
		return "socket";
	}
	
}
