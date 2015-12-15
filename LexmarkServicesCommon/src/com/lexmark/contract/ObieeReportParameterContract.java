package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.domain.ObieeReportParameter;

public class ObieeReportParameterContract implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -208880851368407138L;
	ObieeReportParameter obieeReportParameter;

	public ObieeReportParameter getObieeReportParameter() {
		return obieeReportParameter;
	}

	public void setObieeReportParameter(ObieeReportParameter obieeReportParameter) {
		this.obieeReportParameter = obieeReportParameter;
	}
	
}