package de.jsfpraxis.special.components;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily = Greeting.COMPONENT_FAMILY, rendererType = GreetingRenderer.RENDERER_TYPE)
public class GreetingRenderer extends Renderer {

	public static final String RENDERER_TYPE = "jsfpraxis.GreetingRenderer";
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		String message = (String) component.getAttributes().get("message");
		String to = (String) component.getAttributes().get("to");
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", null);
		writer.writeAttribute("id", component.getClientId(context), null);
		writer.writeAttribute("style", "color:blue;", null);
		writer.write(message + " " + to);
		writer.startElement("br", null); writer.endElement("br");
	}


	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		String closing = (String) component.getAttributes().get("closing");
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("br", null); writer.endElement("br");
		writer.write(closing);
		writer.endElement("div");
	}

}
