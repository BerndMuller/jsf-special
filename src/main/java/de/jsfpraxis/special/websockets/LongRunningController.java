package de.jsfpraxis.special.websockets;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class LongRunningController implements Serializable {
	
	@Inject
	LongRunningService longRunningService;
	
	@Inject
	Logger logger;
	
    @Inject 
    @Push
    private PushContext running; // used to start long running processes
	
	public void callLongRunningService() {
		logger.info("Gebe aufwendige Berechnung in Auftrag.");
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		longRunningService.callService(completableFuture);
		completableFuture.whenCompleteAsync(this::completed);
		logger.info("Beauftragung beendet.");
	}

	private void completed(String result, Throwable throwable) {
		logger.info("Aufwendige Berechnung terminiert. Ergebnis: " + result);
		running.send(result);
	}
}
