package com.lexmark.service.impl.real.domain;

/**
 * @author Vano
 * Mapping file: do-suppliescatalogdo-mapping.xml
 */

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

public class SuppliesCatalogDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8036141855504322469L;
	
	private String contractNumber;
	private String contractLineItemId;
	private String salesOrg;
	private String soldToNumber;
	private String billingModel;
	
	
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getContractLineItemId() {
		return contractLineItemId;
	}
	public void setContractLineItemId(String contractLineItemId) {
		this.contractLineItemId = contractLineItemId;
	}
	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getSoldToNumber() {
		return soldToNumber;
	}
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}
	public String getBillingModel() {
		return billingModel;
	}
	public void setBillingModel(String billingModel) {
		this.billingModel = billingModel;
	}

}
