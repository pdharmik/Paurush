package com.lexmark.contract;

import java.io.Serializable;
import java.util.Map.Entry;

import com.lexmark.contract.api.SearchContractBase;

public class PaymentListContract extends SearchContractBase implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7780561600287504031L;

	private String mdmId;
	private String mdmLevel;
	private String soldToNumber;
	private String agreementId;	 
	private boolean hardwareFlag;
	private String contractNumber;

	public String getMdmId() {
		return mdmId;
	}

	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	@Override
	public PaymentListContract clone() throws CloneNotSupportedException {
		PaymentListContract newContract = new PaymentListContract();
		// PaymentListContract
		newContract.setMdmId(this.getMdmId());
		newContract.setMdmLevel(this.getMdmLevel());
		// SearchContractBase
		newContract.setIncrement(this.getIncrement());
		newContract.setNewQueryIndicator(this.isNewQueryIndicator());
		newContract.setStartRecordNumber(this.getStartRecordNumber());

		for (Entry<String, Object> entry : this.getFilterCriteria().entrySet())
			newContract.getFilterCriteria().put(entry.getKey(), entry.getValue());
		for (Entry<String, Object> entry : this.getSearchCriteria().entrySet())
			newContract.getSearchCriteria().put(entry.getKey(), entry.getValue());
		for (Entry<String, Object> entry : this.getSortCriteria().entrySet())
			newContract.getSortCriteria().put(entry.getKey(), entry.getValue());
		// ContractBase
		newContract.setSessionHandle(this.getSessionHandle());

		return newContract;
	}

	public boolean isHardwareFlag() {
		return hardwareFlag;
	}

	public void setHardwareFlag(boolean hardwareFlag) {
		this.hardwareFlag = hardwareFlag;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
}
