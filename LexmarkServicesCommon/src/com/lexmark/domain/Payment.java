package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {

	private static final long serialVersionUID = 152928248221891476L;

	private String paymentId;
	private Date dateCreated;
	private String paymentNumber;
	private String providerReferenceNumber;
	private ListOfValues paymentStatus;
	private double paymentTotal;
	private String checkNumber;
	private String payableToName;
	private String partnerAgreement;
	private Account partnerAccount;
	private double supplyAgreementFee;
	
	
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public String getProviderReferenceNumber() {
		return providerReferenceNumber;
	}

	public void setProviderReferenceNumber(String providerReferenceNumber) {
		this.providerReferenceNumber = providerReferenceNumber;
	}

	public ListOfValues getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(ListOfValues paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public double getPaymentTotal() {
		return paymentTotal;
	}

	public void setPaymentTotal(double paymentTotal) {
		this.paymentTotal = paymentTotal;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public String getPayableToName() {
		return payableToName;
	}

	public void setPayableToName(String payableToName) {
		this.payableToName = payableToName;
	}

	public String getPartnerAgreement() {
		return partnerAgreement;
	}

	public void setPartnerAgreement(String partnerAgreement) {
		this.partnerAgreement = partnerAgreement;
	}

	public Account getPartnerAccount() {
		return partnerAccount;
	}

	public void setPartnerAccount(Account partnerAccount) {
		this.partnerAccount = partnerAccount;
	}

	public void setSupplyAgreementFee(double supplyAgreementFee) {
		this.supplyAgreementFee = supplyAgreementFee;
	}

	public double getSupplyAgreementFee() {
		return supplyAgreementFee;
	}
}
