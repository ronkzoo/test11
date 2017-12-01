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
    public void greeting(HelloMessage message,SimpMessageHeaderAccessor headerAccessor) throws Exception {
		headerAccessor.getSessionAttributes().put("username", message);
		log.debug(headerAccessor.getSessionId());
    }

	@MessageMapping("/chat")
	@SendTo("/topic/")
	public void chat(HelloMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		log.debug(headerAccessor.getSessionAttributes().get("username").toString());
		log.debug(headerAccessor.getSessionId());
	}

}
