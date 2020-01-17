package de.jsfpraxis.special.jsfconfig;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ExceptionController {

	public String ae() {
		throw new ArithmeticException();
	}

	public String aioobe() {
		throw new ArrayIndexOutOfBoundsException();
	}

}
