package com.lexmark.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.ObieeReportParameter;

public class ObieeReportParameterListResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6040318033605381669L;
	private List<ObieeReportParameter> obieeReportParameterList = new ArrayList<ObieeReportParameter>();

	public List<ObieeReportParameter> getObieeReportParameterList() {
		return obieeReportParameterList;
	}

	public void setObieeReportParameterList(
			List<ObieeReportParameter> obieeReportParameterList) {
		this.obieeReportParameterList = obieeReportParameterList;
	}

}
