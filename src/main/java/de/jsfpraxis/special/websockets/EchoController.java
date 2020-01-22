package de.jsfpraxis.special.websockets;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@Named
@ApplicationScoped
public class EchoController {
	
	private Session session;
	
	@Inject
	Logger logger;
	
	@Inject
	ExternalContext externalContext;
	
	public void sendMessageFromClientToServer() throws DeploymentException, IOException {
        session.getBasicRemote().sendText("Nachricht vom Client");
	}

	@PostConstruct
	public void init() {
		String contextPath = externalContext.getApplicationContextPath();
		String serverName = externalContext.getRequestServerName();
		int serverPort = externalContext.getRequestServerPort();
		String uri = "ws://" + serverName + ":" + serverPort + contextPath + "/echo";
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
        	session = container.connectToServer(EchoClient.class, URI.create(uri));
		} catch (DeploymentException | IOException e) {
			e.printStackTrace();
		}
        logger.info("Client mit Server " + uri + " verbunden. Session ist " + session.getId());
	}
	
	
	@PreDestroy
	public void clean() {
        try {
			session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Session " + session.getId() + " planmäßig geschlossen."));
		} catch (IOException e) {
			e.printStackTrace();
		}
        logger.info("Session " + session.getId() + " geschlossen.");
	}
}
