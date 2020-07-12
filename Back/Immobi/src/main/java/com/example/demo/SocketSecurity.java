package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;


@Configuration
public class SocketSecurity extends AbstractSecurityWebSocketMessageBrokerConfigurer{
	@Override 
	protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
	        messages.simpDestMatchers("/ws", "/ws/**").permitAll();
	}
	@Override
	protected boolean sameOriginDisabled() {
        return true;
	}
}
