package com.example.demo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
/*
 * Ce class est juste pour verifier les sockets et voir le mecanisme de broadcast ou l'envoi
 * d'un msg à un seul client.
 */
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class SocketCont {
	
	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/message")
	public String ws(@Payload String message, Principal principal) {
       System.out.println( principal.getName() );
		for(int cmp = 0; cmp < 10; cmp++) {
			template.convertAndSend("/rt/demande", cmp);
		}
		return "socket";
	}
	
	@SubscribeMapping("/feed")
    public void onAppSubscription(Principal principal) {
        System.out.println("Subscription request to: /app/feed >>> " + principal.getName());
        template.convertAndSendToUser(principal.getName(), "/queue/demande2", "Test Message");
	}
	
	@EventListener(SessionConnectEvent.class)
	public void handleWebsocketConnectListner(SessionConnectEvent event) {
	    //System.out.println("Received a new web socket connection : " + event.getUser().getName());

	}
}
