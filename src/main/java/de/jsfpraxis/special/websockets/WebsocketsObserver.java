package de.jsfpraxis.special.websockets;

import java.util.logging.Logger;

import javax.enterprise.event.Observes;
import javax.faces.event.WebsocketEvent;
import javax.faces.event.WebsocketEvent.Closed;
import javax.faces.event.WebsocketEvent.Opened;
import javax.inject.Inject;

public class WebsocketsObserver {

	@Inject
	Logger logger;
	
	public void onOpen(@Observes @Opened WebsocketEvent event) {
		logger.info("Websocket channel '" + event.getChannel() + "' opened");
	}
	
	public void onClose(@Observes @Closed WebsocketEvent event) {
		logger.info("Websocket channel '" + event.getChannel() + "' closed");
	}
	
}
