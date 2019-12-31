package de.jsfpraxis.special.components;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * Komponente, die einfach nur zu <code>Hello World!</code> als Text (kein Tag) gerendert wird.
 * 
 * @author Bernd Müller
 *
 */
@FacesComponent(value = "jsfpraxis.HelloWorld", tagName = "helloWorld", createTag = true)
public class HelloWorld extends UIOutput {
	
	private static final Logger logger = Logger.getLogger(HelloWorld.class.getCanonicalName());

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		logger.info("family: " + this.getFamily());
		logger.info("renderer type: " + this.getRendererType());
		ResponseWriter writer = context.getResponseWriter();
		writer.write("Hello World!");
	}

//	@Override
//	public void encodeEnd(FacesContext context) throws IOException {
//		ResponseWriter writer = context.getResponseWriter();
//		writer.write("Hello World!");
//	}

}
