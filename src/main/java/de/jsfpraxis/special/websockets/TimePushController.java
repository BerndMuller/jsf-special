package de.jsfpraxis.special.websockets;

import java.time.LocalTime;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TimePushController {
	
	private LocalTime serverTime;
	
	@Inject
	Logger logger;
	
    @Inject 
    @Push
    private PushContext pushTime;
    
    @Inject
    TimeEventGenerator timeEventGenerator;

    public TimePushController() {
	}

	public void onTimeEvent(@Observes LocalTime localTime) {
		logger.info("onTimeEvent() called");
    	this.serverTime = localTime; 
    	pushTime.send("updateTime");
    }
	
	public void startTimer() {
		timeEventGenerator.startTimerEvents();
	}

	public void stopTimer() {
		timeEventGenerator.stopTimerEvents();
	}

	
	// Getter und Setter
	public LocalTime getServerTime() {
		return serverTime;
	}

}
