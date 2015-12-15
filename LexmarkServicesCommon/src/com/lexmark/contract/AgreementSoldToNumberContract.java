package com.lexmark.contract;

import java.io.Serializable;

import com.lexmark.contract.api.MdmSearchContractBase;

public class AgreementSoldToNumberContract extends MdmSearchContractBase implements Serializable {

	private static final long serialVersionUID = -4721995865194618883L;

	private String agreementId;
	private String contractNumber;
	
	public String getAgreementId() {
		return agreementId;
	}
	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
}
