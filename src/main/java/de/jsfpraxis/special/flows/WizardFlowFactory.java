package de.jsfpraxis.special.flows;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

@SuppressWarnings("serial")
public class WizardFlowFactory implements Serializable {
	
	private static final String FLOW_ID = "wizard";
	
    @Produces 
    @FlowDefinition
    public Flow defineFlow(@FlowBuilderParameter FlowBuilder flowBuilder) {
        flowBuilder.id("", FLOW_ID);
        
        flowBuilder.viewNode("data1", "/flows/data-set-1.xhtml").markAsStartNode();
        flowBuilder.viewNode("data2", "/flows/data-set-2.xhtml");
        flowBuilder.viewNode("data3", "/flows/data-set-3.xhtml");
        
        flowBuilder.navigationCase()
        	.fromViewId("/flows/data-set-1.xhtml")
        	.fromOutcome("toDataSet2")
        	.toViewId("/flows/data-set-2.xhtml")
        	.redirect();

        flowBuilder.navigationCase()
        	.fromViewId("/flows/data-set-2.xhtml")
        	.fromOutcome("toDataSet3")
        	.toViewId("/flows/data-set-3.xhtml")
        	.redirect();

        flowBuilder.navigationCase()
        	.fromViewId("/flows/data-set-2.xhtml")
        	.fromOutcome("toDataSet1")
        	.toViewId("/flows/data-set-1.xhtml")
        	.redirect();
        
        flowBuilder.returnNode("complete").fromOutcome("/flows/completed.xhtml");
        flowBuilder.returnNode("abort").fromOutcome("/flows/aborted.xhtml");
        
        flowBuilder.initializer("#{wizardData.flowStarted}");
        flowBuilder.finalizer("#{wizardData.flowTerminated}");

        return flowBuilder.getFlow();
    }
    
}
