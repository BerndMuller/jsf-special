package de.jsfpraxis.special.websockets;

import java.util.concurrent.CompletableFuture;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class LongRunningService {

    @Asynchronous
    public void callService(CompletableFuture<String> completableFuture) {
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
        completableFuture.complete("Ganz aufwendig berechnetes Ergebnis!");
    }

}
