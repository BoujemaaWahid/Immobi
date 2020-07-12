package com.example.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class SocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
	
		stompEndpointRegistry.addEndpoint("/ws")
				.setAllowedOrigins("http://localhost:4200").withSockJS();		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/rt");
	}
}
