package de.jsfpraxis.special.websockets;

import java.time.LocalTime;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Stateless
public class TimeEventGenerator {
	
	private static final Logger logger = Logger.getLogger(TimeEventGenerator.class.getCanonicalName());
	
	@Inject
	Event<LocalTime> timerEvent;
	
	@Resource
	TimerService timerService; 

	//@Schedule(second = "*", minute = "*" , hour = "*", persistent = false) // some declarative alternative
	public void generate() {
		timerEvent.fire(LocalTime.now());
		logger.info("Timer timed out");
	}
	
	
	public void startTimerEvents() {
		timerService.createIntervalTimer(0, 1000, new TimerConfig("Web-Socket-Timer", false));
		logger.info("New Timer startet");
	}

	public void stopTimerEvents() {
		Collection<Timer> timers = timerService.getTimers();
		logger.info("Cancelling " + timers.size() + " timers");
		// ALLE Timer, die existieren, beenden
		for (Timer timer : timers) {
			timer.cancel();
		}
	}
	
	@Timeout
	private void fireTimerEvent() {
		timerEvent.fire(LocalTime.now());
		logger.info("Timer timed out");
	}

}
