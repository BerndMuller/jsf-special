package de.jsfpraxis.special.stateless;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
//@RequestScoped
public class StatelessController implements Serializable {

	private String input1;
	private String input2;
	private boolean showInput1;
	private boolean showInput2;
	
	@Inject
	Logger logger;
	
	@Inject
	FacesContext facesContext;

	public StatelessController() {
	}
	
	
	public void stay() {
		// stay on view
	}
	
	public void toggleInput2(AjaxBehaviorEvent event) {
		HtmlSelectBooleanCheckbox checkbox = (HtmlSelectBooleanCheckbox) event.getComponent();
		List<UIComponent> components = checkbox.getParent().getChildren();
		if ((boolean) checkbox.getValue()) {
			// add input
			HtmlInputText inputText = new HtmlInputText();
			ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
			ELContext elContext = facesContext.getELContext();
			inputText.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{statelessController.input2}", String.class));
			inputText.setId("input2");
			components.add(inputText);
		} else {
			// delete input
			logger.info("found: " + facesContext.getViewRoot().findComponent("form:input2"));
			HtmlInputText inputText = (HtmlInputText) facesContext.getViewRoot().findComponent("form:input2");
			if (inputText == null) {
				// stateless, noop
			} else {
				checkbox.getParent().getChildren().remove(inputText);	
			}
		}
	}

	public void inputChanged(ValueChangeEvent vce) {
		logger.info("Alter Wert: " + vce.getOldValue());
		logger.info("Neuer Wert: " + vce.getNewValue());
	}
	
	
	@PostConstruct
	public void init() {
		logger.info("created");
	}
	
	@PreDestroy
	public void cleanUp() {
		logger.info("destroyed");
	}
	
	
	// Getter and Setter
	public String getInput1() {
		return input1;
	}
	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}
	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public boolean isShowInput1() {
		return showInput1;
	}
	public void setShowInput1(boolean showInput1) {
		this.showInput1 = showInput1;
	}

	public boolean isShowInput2() {
		return showInput2;
	}
	public void setShowInput2(boolean showInput2) {
		this.showInput2 = showInput2;
	}
	
}
