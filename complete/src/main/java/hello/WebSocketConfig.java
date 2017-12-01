package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Bean
	public HandshakeInterceptor handleInterceptor(){
		return new HttpHandshakeInterceptor();
	}

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").addInterceptors(handleInterceptor()).withSockJS();
    }

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    	log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		log.debug("---------------------------------------------------------------------");
		for (HandlerMethodArgumentResolver resolver: argumentResolvers ) {
			log.debug(resolver.toString());
		}
		super.addArgumentResolvers(argumentResolvers);
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		super.configureClientInboundChannel(registration);
	}
}