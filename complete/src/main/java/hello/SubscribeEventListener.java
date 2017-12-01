package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by rasset on 2017-12-01.
 */
@Component
@Slf4j
public class SubscribeEventListener {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = sha.getSessionId();
		log.debug("sessionId : ",sessionId);
		log.debug(event.getSource().toString());
		log.debug(event.toString());
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		log.debug("eieieieiieieieieieiie20202030402034023040eeeoe");
		log.debug("eieieieiieieieieieiie20202030402034023040eeeoe");
		log.debug("eieieieiieieieieieiie20202030402034023040eeeoe");
		log.debug("eieieieiieieieieieiie20202030402034023040eeeoe");
	}

}
