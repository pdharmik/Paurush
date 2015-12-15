package com.lexmark.form;

import java.io.Serializable;

import com.lexmark.domain.ServiceRequestAP;

public class SRForm extends BaseForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceRequestAP srDetail;	
	private String srListXML;
	public String getSrListXML() {
		return srListXML;
	}
	
	public void setSrListXML(String srListXML) {
		this.srListXML = srListXML;
	}

	public ServiceRequestAP getSrDetail() {
		return srDetail;
	}

	public void setSrDetail(ServiceRequestAP srDetail) {
		this.srDetail = srDetail;
	}

}
