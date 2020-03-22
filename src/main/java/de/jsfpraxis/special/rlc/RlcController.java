package de.jsfpraxis.special.rlc;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Resource-Library-Contract-Controller
 * 
 * 
 * 
 * 
 * @author bernd
 *
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class RlcController implements Serializable {
	
	private Contract contract;

	public RlcController() {
	}
	
	public void toggle() {
		if (contract == Contract.CONTRACTA) {
			contract = Contract.CONTRACTB;
		} else {
			contract = Contract.CONTRACTA;
		}
	}

	// Getter and Setter
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@PostConstruct
	public void init() {
		contract = Contract.CONTRACTA;
	}
}

enum Contract {
	
	CONTRACTA, CONTRACTB;
	
}
