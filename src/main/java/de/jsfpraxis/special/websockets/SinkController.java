package de.jsfpraxis.special.websockets;

import java.io.Serializable;
import java.util.UUID;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class SinkController implements Serializable {

	private String id;

	public SinkController() {
		id = UUID.randomUUID().toString().substring(0, 8);
	}

	public String getId() {
		return id;
	}

}
