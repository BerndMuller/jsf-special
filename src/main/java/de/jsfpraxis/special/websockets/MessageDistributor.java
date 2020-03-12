package de.jsfpraxis.special.websockets;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class MessageDistributor {
	
	private String globalMessage;
	private String sessionMessage;
	private String userMessage;
	private String userId;

	@Inject
	Logger logger;
	
    @Inject 
    @Push(channel = "global")
    private PushContext globalPushContext;

    @Inject 
    @Push(channel = "session")
    private PushContext sessionPushContext;

    @Inject 
    @Push(channel = "user")
    private PushContext userPushContext;

    
    public void distributeMessage() {
    	logger.info("");
    	globalPushContext.send(globalMessage);
    }

    public void sendMessageToUser() {
    	userPushContext.send(userMessage, userId);
    }

    
    // Getter und Setter
	public String getGlobalMessage() {
		return globalMessage;
	}
	public void setGlobalMessage(String globalMessage) {
		this.globalMessage = globalMessage;
	}

	
	public String getSessionMessage() {
		return sessionMessage;
	}
	public void setSessionMessage(String sessionMessage) {
		this.sessionMessage = sessionMessage;
	}

	
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
}
