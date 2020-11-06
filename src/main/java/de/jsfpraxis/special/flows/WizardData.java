package de.jsfpraxis.special.flows;

import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.annotation.FlowMap;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@FlowScoped("wizard")
public class WizardData implements Serializable {
	
	private static final Logger logger = Logger.getLogger(WizardData.class.getCanonicalName());
	
	private String data1;
	private String data2;
	private String data3;
	
	@Inject
	@FlowMap
	Map<Object, Object> currentFlowScope;
	
	@Inject
	UIViewRoot viewRoot;
	
	public WizardData() {
	}
	
	public void flowStarted() {
		logger.info("Flow 'wizard' started.");
		currentFlowScope.put("someKey", "some value");
	}
	
	public void flowTerminated() {
		// viewId ist "/flows/completed.xhtml " oder "/flows/aborted.xhtml"
		boolean completed = viewRoot.getViewId().contains("completed");
		logger.info("Flow 'wizard' " + (completed ? "completed" : "aborted"));
		if (completed) {
			logger.info(String.format("data1: %s, data2: %s, data3: %s", data1, data2, data3));	
		}
	}
	
	// Getter and Setter
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	
}
