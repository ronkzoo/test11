package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class GreetingController {

    @MessageMapping("/hello")
    public void hello(HelloMessage message,SimpMessageHeaderAccessor headerAccessor) throws Exception {
		headerAccessor.getSessionAttributes().put(headerAccessor.getSessionId(), new User(headerAccessor.getSessionId(), message.getName()));
		log.debug(headerAccessor.getSessionId());
    }

	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public Greeting chat(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
    	log.debug("당신의 사용자 정보는? ");
		User user = (User)headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId());
		log.debug(headerAccessor.getSessionAttributes().get(headerAccessor.getSessionId()).toString());
		log.debug(headerAccessor.getSessionId());
		return new Greeting(message.getMessage(), user);
	}

}
