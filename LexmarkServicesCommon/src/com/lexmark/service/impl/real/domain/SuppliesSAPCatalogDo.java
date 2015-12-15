package com.lexmark.service.impl.real.domain;

/**
 * @author Vano
 * Mapping file: do-suppliessapcatalogdo-mapping.xml
 */

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

public class SuppliesSAPCatalogDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5661234743623729341L;
	
	private String contractNumber;
	private String contractLine;
	private String soldToNumber;
	private String soldToType;
	private String contractSAPSatus;
	
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	
	public String getContractLine() {
		return contractLine;
	}
	public void setContractLine(String contractLine) {
		this.contractLine = contractLine;
	}
	public String getSoldToNumber() {
		return soldToNumber;
	}
	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}
	public String getSoldToType() {
		return soldToType;
	}
	public void setSoldToType(String soldToType) {
		this.soldToType = soldToType;
	}
	public String getContractSAPSatus() {
		return contractSAPSatus;
	}
	public void setContractSAPSatus(String contractSAPSatus) {
		this.contractSAPSatus = contractSAPSatus;
	}
}
