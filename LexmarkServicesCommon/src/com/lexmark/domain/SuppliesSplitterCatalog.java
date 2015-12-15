package com.lexmark.domain;

import java.io.Serializable;

public class SuppliesSplitterCatalog implements Serializable{
private String contractNumber;
private String contractLineItemId;
private String billingModel;
private String soldToNumber;
private String salesOrg;
private String providerContractNo;
private String providerOrderNumber;

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
public String getBillingModel() {
	return billingModel;
}
public void setBillingModel(String billingModel) {
	this.billingModel = billingModel;
}
public String getSoldToNumber() {
	return soldToNumber;
}
public void setSoldToNumber(String soldToNumber) {
	this.soldToNumber = soldToNumber;
}
public String getSalesOrg() {
	return salesOrg;
}
public void setSalesOrg(String salesOrg) {
	this.salesOrg = salesOrg;
}
public String getProviderContractNo() {
	return providerContractNo;
}
public void setProviderContractNo(String providerContractNo) {
	this.providerContractNo = providerContractNo;
}
public String getProviderOrderNumber() {
	return providerOrderNumber;
}
public void setProviderOrderNumber(String providerOrderNumber) {
	this.providerOrderNumber = providerOrderNumber;
}
}
