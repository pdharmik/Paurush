package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * @author dtsamalshvili 
 * 
 * Mapping file:   do-companycodedo-mapping.xml
 */

public class CompanyCodeDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 5893524433013626475L;
	
	private String companyDescription;
	private String value;
	
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
}
