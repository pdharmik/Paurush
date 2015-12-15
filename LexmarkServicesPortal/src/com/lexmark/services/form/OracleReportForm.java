package com.lexmark.services.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ObieeReportParameter;

public class OracleReportForm  implements Serializable {
	
	private static final long serialVersionUID = 7963671566873662832L;
	private List<ObieeReportParameter> parameters = new ArrayList<ObieeReportParameter>();
	/**
	 * 
	 * @return List 
	 */
	public List<ObieeReportParameter> getParameters() {
		return parameters;
	}
	/**
	 * 
	 * @param parameters 
	 */
	public void setParameters(List<ObieeReportParameter> parameters) {
		this.parameters = parameters;
	}
}
