package com.lexmark.service.impl.real.domain;

/**
 * @author imdzeluri     Mapping file: do-accountagreementsoldtodo-mapping.xml
 */
import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

public class AccountAgreementSoldToDo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1737091093231356434L;
	
	private String accountId;
	private String agreementId;
	private String contactId;
	private String contractName;
	private String contractNumber;
	private String soldTo;
	private String soldToType;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getSoldTo() {
		return soldTo;
	}
	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}
	public String getSoldToType() {
		return soldToType;
	}
	public void setSoldToType(String soldToType) {
		this.soldToType = soldToType;
	}
	
}
