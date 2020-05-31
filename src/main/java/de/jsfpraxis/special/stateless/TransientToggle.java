package de.jsfpraxis.special.stateless;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TransientToggle {

	private boolean isTransient; // because 'transient' ist keyword

	public TransientToggle() {
	}
	
	public void toggle() {
		isTransient = !isTransient;
	}

	// Getter and Setter
	public boolean isTransient() {
		return isTransient;
	}

	public void setTransient(boolean isTransient) {
		this.isTransient = isTransient;
	}
	
	
	
	
}
