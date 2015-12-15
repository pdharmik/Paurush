package com.lexmark.contract;

import java.io.Serializable;
import java.util.Map.Entry;

import com.lexmark.contract.api.SearchContractBase;

public class PaymentRequestListContract extends SearchContractBase implements Serializable, Cloneable {

	private static final long serialVersionUID = 2903380203670392431L;
	private String paymentStatus;
	private String mdmId;
	private String mdmLevel;

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

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

	@Override
	public PaymentRequestListContract clone() {
		PaymentRequestListContract newContract = new PaymentRequestListContract();
		// PaymentRequestListContract
		newContract.setMdmId(this.getMdmId());
		newContract.setMdmLevel(this.getMdmLevel());
		newContract.setPaymentStatus(this.getPaymentStatus());
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
}
