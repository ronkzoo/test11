package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@Slf4j
public class GreetingController {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

    @MessageMapping("/hello")
    public void hello(HelloMessage message,SimpMessageHeaderAccessor headerAccessor) throws Exception {
		headerAccessor.getSessionAttributes().put(headerAccessor.getSessionId(), new User(headerAccessor.getSessionId(), message.getName(), null));
		log.debug(headerAccessor.getSessionId());
    }

	@MessageMapping("/join")
	public Greeting join(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		User user = (User)headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId());
		User targetUser = user.getTargetUser();
		if(targetUser == null) {
			Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
			sessionAttributes.forEach((key,value)->{
				if(key != headerAccessor.getSessionId())	 {
					if(value != null) {
						User u = (User)value;
						if(u.getTargetUser() == null) {
							log.debug("u.getUsername() {} ", u.getUsername());
							user.setTargetUser(u);
							log.debug("user set correct{}", headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId()));
							u.setTargetUser(user);
						}
					}
				}
			});
		}
		return new Greeting(HttpStatus.OK.toString(), user);
	}

	@SendTo("/topic/matching")
	public Greeting matching(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		User user = (User)headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId());
		User targetUser = user.getTargetUser();
		if(targetUser == null) {
			Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
			sessionAttributes.forEach((key,value)->{
				if(key != headerAccessor.getSessionId())	 {
					if(value != null) {
						User u = (User)value;
						if(u.getTargetUser() == null) {
							log.debug("u.getUsername() {} ", u.getUsername());
							user.setTargetUser(u);
							log.debug("user set correct{}", headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId()));
							u.setTargetUser(user);
						}
					}
				}
			});
		}
		return new Greeting(HttpStatus.OK.toString(), user);
	}

	@MessageMapping("/chat")
	public Greeting chat(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
    	log.debug("당신의 사용자 정보는? ");
		User user = (User)headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId());
		log.debug(headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId()).toString());
		log.debug(headerAccessor.getSessionId());
		this.jmsMessagingTemplate.convertAndSend(user.getTargetUser().getId(), message.getMessage());
		return new Greeting(message.getMessage(), user);
	}

	@SendTo("/topic/receive")
	public Greeting receive(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		User user = (User)headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId());
		User targetUser = user.getTargetUser();
		if(targetUser == null)
			throw new Exception("error! ");

		HelloMessage receive = (HelloMessage) this.jmsMessagingTemplate.receive(user.getId());
		return new Greeting(receive.getMessage(), user);
	}

}
