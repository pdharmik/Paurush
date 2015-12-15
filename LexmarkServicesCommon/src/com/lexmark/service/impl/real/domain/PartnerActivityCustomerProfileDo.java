package com.lexmark.service.impl.real.domain;

import com.amind.common.domain.BaseEntity;


/**
 * do-mapping: "do-partneractivitycustomerprofile-mapping.xml"
 */

public class PartnerActivityCustomerProfileDo extends BaseEntity{

	private String fieldName;
	private String coordinatesXPreDebriefRFV;
	private String coordinatesYPreDebriefRFV;

	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getCoordinatesXPreDebriefRFV() {
		return coordinatesXPreDebriefRFV;
	}

	public void setCoordinatesXPreDebriefRFV(String coordinatesXPreDebriefRFV) {
		this.coordinatesXPreDebriefRFV = coordinatesXPreDebriefRFV;
	}

	public String getCoordinatesYPreDebriefRFV() {
		return coordinatesYPreDebriefRFV;
	}

	public void setCoordinatesYPreDebriefRFV(String coordinatesYPreDebriefRFV) {
		this.coordinatesYPreDebriefRFV = coordinatesYPreDebriefRFV;
	}

}
