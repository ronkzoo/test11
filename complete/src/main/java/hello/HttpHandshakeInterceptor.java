package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by rasset on 2017-12-01.
 */
@Slf4j
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			log.debug("dkdkdkdkkdkd");
			if(request instanceof HttpServletRequest) {
				HttpServletRequest req = (HttpServletRequest) request;
				Enumeration<String> parameterNames = req.getParameterNames();
				while(parameterNames.hasMoreElements()) {
					log.debug(parameterNames.nextElement());
				}
			}
			if(attributes != null) {
				Set<Map.Entry<String, Object>> entries = attributes.entrySet();
				Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
				while(iterator.hasNext()) {
					Map.Entry<String, Object> next = iterator.next();
					log.debug("key {}, value{}",next.getKey() , next.getValue());
				}
			}

		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
		log.debug("afterHandShake");
	}
}
