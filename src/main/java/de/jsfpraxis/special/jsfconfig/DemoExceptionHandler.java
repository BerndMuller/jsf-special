package de.jsfpraxis.special.jsfconfig;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 * 
 * @author bernd
 *
 */
public class DemoExceptionHandler extends ExceptionHandlerWrapper {
	
	private ExceptionHandler parent;
	
	@SuppressWarnings("deprecation")
	public DemoExceptionHandler(ExceptionHandler parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return parent;
	}

	@Override
	public void handle() throws FacesException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = facesContext.getApplication()
				.getNavigationHandler();
		for (ExceptionQueuedEvent event : getUnhandledExceptionQueuedEvents()) {
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable t = context.getException();
			// evtl. mehrfach eingepackt, also iterativ auspacken
			while ((t instanceof FacesException || t instanceof ELException) && t.getCause() != null) {
				System.out.println("wrapped exception: " + t.getClass());
				t = t.getCause();
			}
			if (t instanceof ArithmeticException) {
				navigationHandler.handleNavigation(facesContext, null, "/jsfconfig/caught-arithmetic-exception?faces-redirect=true");
			} else if (t instanceof ArrayIndexOutOfBoundsException) {
				navigationHandler.handleNavigation(facesContext, null, "/jsfconfig/caught-array-index-out-of-bound-exception?faces-redirect=true");
			} 
		}
	}

}
