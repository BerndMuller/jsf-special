package de.jsfpraxis.special.stateless;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
//@RequestScoped
//@ViewScoped
@SessionScoped
public class SimplestCaseController implements Serializable {

	private String input;
	
	@Inject
	Logger logger;
	
	public SimplestCaseController() {
	}
	
	public void stay() {
		logger.info("input: " + input);
	}
	
	@PostConstruct
	public void init() {
		logger.info("created");
	}
	
	@PreDestroy
	public void cleanUp() {
		logger.info("destroyed");
	}
	
	// Getter und Setter
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	
}
