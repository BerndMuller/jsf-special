package de.jsfpraxis.special.websockets;

import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

@ServerEndpoint("/echo")
public class EchoEndpoint {
	
	private static Session session;
	
	@Inject
	Logger logger;

	//@OnMessage
	public void receiveTextMessage(String message) {
		logger.info("Nachricht erhalten: \"" + message + "\"");
	}
	
	@OnMessage
	public void receiveTestMessage(Session session, String message) {
		logger.info("Nachricht erhalten: \"" + message + "\". Schicke was zurück");
		try {
			session.getBasicRemote().sendText("Endpoint: \"" + message + "\" erhalten, schicke \"" + new StringBuilder(message).reverse() + "\" zurück.");
		} catch (IOException e) {
		}
	}

	//@OnOpen
	public void open() {
		logger.info("geht auch");

	}

	@OnOpen
	public void open(Session session) {
		logger.info("Verbindung aufgebaut von Peer " + session.getId());
		EchoEndpoint.session = session;
	}

	//@OnOpen
	public void open(Session session, EndpointConfig conf) {
		logger.info("Verbindung aufgebaut von Peer: " + session.getId() + " mit Endpoint-Config " + ((ServerEndpointConfig) conf).getPath());
		EchoEndpoint.session = session;
	}

	
	@OnClose
	public void close(Session session) {
		logger.info("Verbindung geschlossen von Peer: " + session.getBasicRemote());
		EchoEndpoint.session = null;
	}

	
	@OnError
	public void error(Throwable t) {
		logger.severe("Verbindungsfehler: " + t.getMessage());
	}
	
	
	public void onTimeEvent(@Observes LocalTime localTime) throws Exception {
		if (session != null) {
			session.getBasicRemote().sendText("Aktuelle Server-Zeit: " + localTime);
		} else {
			logger.info("session ist null");
		}
	}
	
	
	@PostConstruct
	public void init() {
		logger.info("Instance created");
	}
	
	
	@PreDestroy
	public void clean() {
		logger.info("Instance destroyed");
	}
	
}



