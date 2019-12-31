package de.jsfpraxis.special.websocket;

import java.time.LocalTime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TimePushController {
	
	private LocalTime localTime;
	
    @Inject 
    @Push
    private PushContext push;
    
    @Inject
    TimeEventGenerator timeEventGenerator;

    public TimePushController() {
	}

	public void onTimeEvent(@Observes LocalTime localTime) {
		System.out.println("event handler");
    	this.localTime = localTime; 
    	push.send("updateTime");
    }
	
	public void startTimer() {
		timeEventGenerator.startTimerEvents();
	}

	public void stopTimer() {
		timeEventGenerator.stopTimerEvents();
	}

	
	// Getter und Setter
	public LocalTime getLocalTime() {
		return localTime;
	}

}
