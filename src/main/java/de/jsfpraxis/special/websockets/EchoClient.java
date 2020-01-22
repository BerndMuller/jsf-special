package de.jsfpraxis.special.websockets;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class EchoClient {
	
	@Inject
	Logger logger;

	@OnMessage
	public void receiveTextMessage(String message) {
		logger.info("Client hat folgende Nachricht empfangen: " + message);
	}
	
	@OnOpen
	public void open(Session session) {
		logger.info("Client-Endpoint erzeugt für Session " + session.getId());
		//session.getBasicRemote().sendText("Initiale Nachricht nach Öffnen abgeschickt");
	}
	
	@OnClose
	public void close(Session session, CloseReason closeReason) {
		logger.info("Client-Endpoint geschlossen für Session: " + session + ". Grund: " + closeReason);
	}
	
	@OnError
	public void error(Session session, Throwable t) {
		logger.severe("Verbindungsfehler: " + t.getMessage());
	}
	
}
