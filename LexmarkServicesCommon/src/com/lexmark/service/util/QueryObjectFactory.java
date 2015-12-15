package com.lexmark.service.util;

import com.amind.data.service.ActionEvent;
import com.amind.data.service.ExecutionMode;
import com.amind.data.service.QueryObject;
import com.lexmark.contract.api.SearchContractBase;

/**
 * 
 * 
 * @author Souritra Das
 * @since Aug-01-2011
 */
public class QueryObjectFactory {

    private SearchContractBase contract; // Domain object which will set query
					 // property.
    private Class domainClass; // Domain class which need to be bind with
			       // relation realization.
    private ExecutionMode executionMode; // overrides default mode i.e
					 // forwordOnly.
    private String componentSearchSpec; // Search Expression

    public String getComponentSearchSpec() {
	return componentSearchSpec;
    }

    public void setComponentSearchSpec(String componentSearchSpec) {
	this.componentSearchSpec = componentSearchSpec;
    }

    public ExecutionMode getExecutionMode() {
	return executionMode;
    }

    public void setExecutionMode(ExecutionMode executionMode) {
	this.executionMode = executionMode;
    }

    public Class getDomainClass() {
	return domainClass;
    }

    public void setDomainClass(Class domainClass) {
	this.domainClass = domainClass;
    }

    public SearchContractBase getContract() {
	return contract;
    }

    public void setContract(SearchContractBase contract) {
	this.contract = contract;
    }

    /**
     * Creates and returns one instances of QueryObject By default ExecutionMode
     * is always forward only. This method gives a chance to set direction other
     * than forward. This method is helpful when fetching dataset for statefull
     * calls where traverse can be in bidirectional.
     * 
     */
    public QueryObject getQueryObjectInstance() {
	QueryObject criteria = new QueryObject(getDomainClass(), ActionEvent.QUERY);
	criteria.setStartRowIndex(getContract().getStartRecordNumber());
	criteria.setNumRows(getContract().getIncrement());
	criteria.addComponentSearchSpec(getDomainClass(), getComponentSearchSpec());
	if (getExecutionMode() != null) {
	    criteria.setExecutionMode(getExecutionMode());
	}
	return criteria;
    }
}
