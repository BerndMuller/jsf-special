package de.jsfpraxis.advanced.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * Komponenten, die einfach nur zu <code>Hello World!</code> als Text (kein Tag) rendered.
 * 
 * @author Bernd Müller
 *
 */
@FacesComponent(value = "jsfpraxis.HelloWorld", tagName = "helloWorld", createTag = true)
public class HelloWorld extends UIOutput {

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.write("Hello World!");
	}

}
