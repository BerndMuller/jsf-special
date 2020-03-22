package de.jsfpraxis.special.rlc;

import java.io.StringWriter;
import java.util.logging.Logger;

import javax.faces.application.ApplicationConfigurationPopulator;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Implementierung eines ApplicationConfigurationPopulator für Resource-Library-Contracts.
 * 
 * <p>
 * Aus Spec 11.4.2 Application Startup Behavior:
 * 
 * <p>
 * Using the java.util.ServiceLoader, locate all implementations of the javax.faces.ApplicationConfigurationResourceDocumentPopulator service. 
 * For each implementation, create a fresh org.w3c.dom.Document instance, configured to be in the XML namespace of the application configuration 
 * resource format, and invoke the implementation’s populateApplicationConfigurationResource() method. If no exception is thrown, add the document 
 * to the list, otherwise log a message and continue.
 * 
 * 
 * @author bernd
 *
 */
public class RlcConfiguration extends ApplicationConfigurationPopulator {

	@Override
	public void populateApplicationConfiguration(Document document) {
		String ns = document.getDocumentElement().getNamespaceURI();
		Element application = document.createElementNS(ns, "application");
		Element resourceLibraryContracts = document.createElementNS(ns, "resource-library-contracts");
		Element contractMapping = document.createElementNS(ns, "contract-mapping");
		Element urlPattern = document.createElementNS(ns, "url-pattern");
		urlPattern.appendChild(document.createTextNode("*"));
		Element contracts = document.createElementNS(ns, "contracts");
		contracts.appendChild(document.createTextNode("contractb")); // hier den Contract eintragen
		contractMapping.appendChild(urlPattern);
		contractMapping.appendChild(contracts);
		resourceLibraryContracts.appendChild(contractMapping);
		application.appendChild(resourceLibraryContracts);
		document.getDocumentElement().appendChild(application);
		Logger.getLogger(RlcConfiguration.class.getCanonicalName()).info(RlcConfiguration.toString(document));
	}

	private static String toString(Document newDoc) {
		try {
			StringWriter sw = new StringWriter();
			DOMSource domSource = new DOMSource(newDoc);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StreamResult sr = new StreamResult(sw);
			transformer.transform(domSource, sr);
			return sw.toString();
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
