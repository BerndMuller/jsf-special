package de.jsfpraxis.advanced.spa;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ContentController {

	private String input;

	public ContentController() {
	}

	
	@PostConstruct
	public void init() {
		Logger.getLogger(ContentController.class.getCanonicalName()).info("Instance created");
		input = "" + this.hashCode();
	}
	
	// Getter und Setter
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
}
