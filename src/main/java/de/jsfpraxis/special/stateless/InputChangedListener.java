package de.jsfpraxis.special.stateless;

import java.util.logging.Logger;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class InputChangedListener implements ValueChangeListener {
	
	private static final Logger logger = Logger.getLogger(InputChangedListener.class.getCanonicalName());
	
	@Override
	public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
		logger.info("Alter Wert: " + event.getOldValue());
		logger.info("Neuer Wert: " + event.getNewValue());
	}

}
