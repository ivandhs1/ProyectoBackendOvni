package com.grupo2.springboot.backend.apirest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
	
	@Override
	  public void configureMessageBroker(MessageBrokerRegistry config) {
	    config.enableSimpleBroker("/topic");
	    config.setApplicationDestinationPrefixes("/app");
	  }

	  @Override
	  public void registerStompEndpoints(StompEndpointRegistry registry) {
		  registry.addEndpoint("/alerta-back")
		  .setAllowedOrigins("http://localhost:4200","https://proyectoovnivinosfront.pages.dev")
		  .withSockJS();
	  }
}
