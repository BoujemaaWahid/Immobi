package com.example.demo;


import java.security.Principal;
import java.util.Map;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class SocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
	
		stompEndpointRegistry.addEndpoint("/ws")
				.setAllowedOrigins("http://localhost:4200")
				.setHandshakeHandler(new AssignPrincipalHandshakeHandler())
				.withSockJS();
				
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/rt", "/feed", "/queue", "/user");
		registry.setUserDestinationPrefix("/user");
	}
	
	public class AssignPrincipalHandshakeHandler extends DefaultHandshakeHandler {
	    private static final String ATTR_PRINCIPAL = "__principal__";

	    @Override
	    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
	        final String name;
	        if (!attributes.containsKey(ATTR_PRINCIPAL)) {
	            name = generateRandomUsername();
	            attributes.put(ATTR_PRINCIPAL, name);
	        } else {
	            name = (String) attributes.get(ATTR_PRINCIPAL);
	        }
	        return new Principal() {
	            @Override
	            public String getName() {
	                return name;
	            }
	        };
	    }
	    //pas besoins de genérer des usernames puisque il aura un seul récepteur.
	    private String generateRandomUsername() {
	    	return "admin";
	    }
	}
}
