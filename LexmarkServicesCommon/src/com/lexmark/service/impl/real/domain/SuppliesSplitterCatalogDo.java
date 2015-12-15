package com.lexmark.service.impl.real.domain;

/**
 * @author Vano
 * Mapping file: do-suppliessplittercatalogdo-mapping.xml
 */

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

public class SuppliesSplitterCatalogDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -8099169975958290851L;
	
	private String contractNumber;
	private String contractLineItemId;
	private String providerContractNo;
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
	public String getProviderContractNo() {
		return providerContractNo;
	}
	public void setProviderContractNo(String providerContractNo) {
		this.providerContractNo = providerContractNo;
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
