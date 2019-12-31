package de.jsfpraxis.special.spa;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class SpaController implements Serializable {
	
	private String content = "content-1";

	public SpaController() {
	}

	// Getter und Setter
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
