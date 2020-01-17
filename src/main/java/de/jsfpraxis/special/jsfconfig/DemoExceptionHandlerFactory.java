package de.jsfpraxis.special.jsfconfig;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class DemoExceptionHandlerFactory extends ExceptionHandlerFactory {
	
	private ExceptionHandlerFactory parent;
	
	@SuppressWarnings("deprecation")
	public DemoExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new DemoExceptionHandler(parent.getExceptionHandler());
	}

}
