package de.jsfpraxis.advanced.components;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.inject.Inject;

/**
 * Baum-Komponente.
 * 
 * @author Bernd Müller
 *
 */
@FacesComponent(value = Tree.COMPONENT_TYPE, tagName = "tree", createTag = true, namespace = "http://jsfpraxis.de/component")
@ResourceDependency(library = "css", name = "expanding-list.css", target = "head")
@ResourceDependency(library = "js", name = "expanding-list.js", target = "body")
public class Tree extends UIComponentBase {
	
	private static final Logger logger = Logger.getLogger(Tree.class.getCanonicalName());
	
	public static final String COMPONENT_TYPE = "jsfpraxis.TreeComponent";
	public static final String COMPONENT_FAMILY = "jsfpraxis.TreeFamily";

	@Inject
	FacesContext facesContext;

	public Tree() {
		//setRendererType(null);
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		logger.info("family: " + this.getFamily());
		logger.info("renderer type: " + this.getRendererType());
		logger.info("renderer: " + this.getRenderer(facesContext));
		logger.info("rendersChildren: " + this.getRendersChildren());
		
		@SuppressWarnings("unchecked")
		TreeNode<String> root = (TreeNode<String>) getAttributes().get("value");
		
		writer.startElement("div", null);
		writer.writeAttribute("id", "tree", null);
		writer.write("<ul is=\"expanding-list\">");
		encodeNodes(writer, root);
		writer.write("</ul>");
		writer.endElement("div");
	}

	
	/**
	 * Rekursives Rendern des Baums.
	 * 
	 * <p>
	 * Muss depth-first sein.
	 * 
	 * @param writer der ResponseWriter der Ausgabe
	 * @param list Liste der Baumknoten
	 * @throws IOException falls Fehler beim Rendern
	 * 
	 */
	private void encodeNodes(ResponseWriter writer, TreeNode<String> node) throws IOException {
		writer.write("<li>" + node.getData());
		if (!node.isLeaf()) {
			writer.write("<ul>");
			for (TreeNode<String> child : node.getChildren()) {
				encodeNodes(writer, child);
			}
			writer.write("</ul>");
		}
		writer.write("</li>");
	}

	
	
}
