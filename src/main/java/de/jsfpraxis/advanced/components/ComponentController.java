package de.jsfpraxis.advanced.components;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ComponentController {
	
	private static final Logger logger = Logger.getLogger(ComponentController.class.getCanonicalName()); 

	@Inject
	FacesContext facesContext;
	
	public void createComponents() {
		Greeting greeting = (Greeting) facesContext.getApplication().createComponent(Greeting.COMPONENT_TYPE);
		logger.info("Greeting instance programmatically created " + greeting);

		HtmlInputText input = (HtmlInputText) facesContext.getApplication().createComponent(HtmlInputText.COMPONENT_TYPE);
		logger.info("HtmlInputText instance programmatically created " + input);
		logger.info("Komponententyp: " + HtmlInputText.COMPONENT_TYPE);
	}
}
