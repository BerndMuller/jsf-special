package de.jsfpraxis.advanced.components;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.inject.Inject;

/**
 * Komponente, die eine Begrüßung realisiert. 
 * 
 * @author Bernd Müller
 *
 */
@FacesComponent(value = Greeting.COMPONENT_TYPE, tagName = "greeting", createTag = true, namespace = "http://jsfpraxis.de/component")
public class Greeting extends UIComponentBase {
	
	private static final Logger logger = Logger.getLogger(Greeting.class.getCanonicalName());
	
	public static final String COMPONENT_TYPE = "jsfpraxis.GreetingComponent";
	public static final String COMPONENT_FAMILY = "jsfpraxis.GreetingFamily";

	@Inject
	FacesContext facesContext;

	public Greeting() {
		//setRendererType(null);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		logger.info("family: " + this.getFamily());
		logger.info("renderer type: " + this.getRendererType());
		logger.info("renderer: " + this.getRenderer(facesContext));
		logger.info("rendersChildren: " + this.getRendersChildren());
		
		String message = (String) getAttributes().get("message");
		String to = (String) getAttributes().get("to");
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", null);
		writer.writeAttribute("id", getClientId(context), null);
		writer.writeAttribute("style", "color:blue;", null);
		writer.write(message + " " + to);
		writer.startElement("br", null); writer.endElement("br");
	}
	

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		String closing = (String) getAttributes().get("closing");
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("br", null); writer.endElement("br");
		writer.write(closing);
		writer.endElement("div");
	}
	
	
	/**
	 * Rendert Kindknoten, allerdings nur, falls <code>getRendersChildren()</code> <code>true</code> zurückliefert.
	 * Also bei Bedarf Kommentar bei der Methode unten entfernen.
	 */
	@Override
	public void encodeChildren(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.write("vor");
		super.encodeChildren(context);
		writer.write("nach");
	}
	
	
//	@Override
//	public boolean getRendersChildren() {
//	    return true;
//	}
}
