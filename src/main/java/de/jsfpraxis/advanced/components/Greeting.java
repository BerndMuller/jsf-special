package de.jsfpraxis.advanced.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * Komponenten, die 
 * 
 * @author Bernd Müller
 *
 */
@FacesComponent(value = "components.HelloWorld", tagName = "helloWorld", createTag = true)
//public class HelloWorld extends UIComponentBase {
public class Greeting extends UIOutput {

	
	// TODO
	// COMPONENT_FAMILY
	// COMPONENT_TYPE
	
//	@Override
//	public String getFamily() {
//		return "jsfpraxis.componentFamily";
//	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", null);
		//writer.writeAttribute("id", getClientId(context), null); // TODO wozu?
		writer.write("Hello World!");
	}

}
