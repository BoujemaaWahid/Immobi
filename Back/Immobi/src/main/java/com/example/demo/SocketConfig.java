package com.example.demo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new TextHandler(), "/ws").setAllowedOrigins("*").withSockJS();
		
	}

	class TextHandler extends TextWebSocketHandler {
		private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			sessions.add(session);
		}
		
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) {
			for(WebSocketSession ws: sessions) {
				try {
					ws.sendMessage(message);
				}catch(Exception ex) { ex.printStackTrace(); }
			}
		}
	}

}
