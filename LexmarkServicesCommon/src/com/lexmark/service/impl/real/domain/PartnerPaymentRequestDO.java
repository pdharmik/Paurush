package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.lexmark.util.LangUtil;

/**
 * mapping file: "do-partnerpaymentrequest-mapping.xml"
 */
public class PartnerPaymentRequestDO extends AccountBasedDo {
	private static final long serialVersionUID = 2932188037269421043L;
	
	private String eligibleToPay;
	private String payEligiblityOverride;
	private String partnerAgreementName;
	private String serviceRequestStatus;
	private String partnerAccountId;
	private String partnerAccountName;
	private String paymentStatus;
	private String paymentNumber;
	private String activityId;
	private String activityDate;
	private String serviceProviderReferenceNumber;
	private String paymentServiceType;
	private String laborPayment;
	private String partsPayment;
	private String additionalPayments;
	private String partnerFee;
	private String serviceRequestId;
	private String serviceRequestNumber;
	private String assetId;
	private String serialNumber;
	private String productTLI;
	private String modelNumber;
	private String productLine;
	private String customerAccountId;
	private String customerAccountName;
	private String paymentId;
	private ArrayList<PartnerPaymentRequestExpenseDO> lineItems;
	private String supplyAgreementFee;
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public Date getActivityDate() {
		return convertStringToDateWithOutMarker(activityDate);
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public String getPaymentServiceType() {
		return paymentServiceType;
	}
	public void setPaymentServiceType(String paymentServiceType) {
		this.paymentServiceType = paymentServiceType;
	}
	public String getLaborPayment() {
		if(StringUtils.isEmpty(laborPayment)){
			return "0";
		}
		return laborPayment;
	}
	public void setLaborPayment(String laborPayment) {
		this.laborPayment = laborPayment;
	}
	public String getPartsPayment() {
		if(StringUtils.isEmpty(partsPayment)) {
			return "0";
		}
		return partsPayment;
	}
	public void setPartsPayment(String partsPayment) {
		this.partsPayment = partsPayment;
	}
	public String getAdditionalPayments() {
		if(StringUtils.isEmpty(additionalPayments)) {
			return "0";
		}
		return additionalPayments;
	}
	public void setAdditionalPayments(String additionalPayments) {
		this.additionalPayments = additionalPayments;
	}
	public String getPartnerFee() {
		if(StringUtils.isEmpty(partnerFee)) {
			return "0";
		}
		return partnerFee;
	}
	public void setPartnerFee(String partnerFee) {
		this.partnerFee = partnerFee;
	}
	public String getServiceRequestId() {
		return serviceRequestId;
	}
	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}
	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getProductTLI() {
		return productTLI;
	}
	public void setProductTLI(String productTLI) {
		this.productTLI = productTLI;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getCustomerAccountId() {
		return customerAccountId;
	}
	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}
	public String getCustomerAccountName() {
		return customerAccountName;
	}
	public void setCustomerAccountName(String customerAccountName) {
		this.customerAccountName = customerAccountName;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	private String mdmLevel;
	
	
	public String getPartnerAccountId() {
		return partnerAccountId;
	}
	public void setPartnerAccountId(String partnerAccountId) {
		this.partnerAccountId = partnerAccountId;
	}
	public String getPartnerAccountName() {
		return partnerAccountName;
	}
	public void setPartnerAccountName(String partnerAccountName) {
		this.partnerAccountName = partnerAccountName;
	}
	public String getEligibleToPay() {
		return eligibleToPay;
	}
	public void setEligibleToPay(String eligibleToPay) {
		this.eligibleToPay = eligibleToPay;
	}
	public String getPayEligiblityOverride() {
		return payEligiblityOverride;
	}
	public void setPayEligiblityOverride(String payEligiblityOverride) {
		this.payEligiblityOverride = payEligiblityOverride;
	}
	public String getPartnerAgreementName() {
		return partnerAgreementName;
	}
	public void setPartnerAgreementName(String partnerAgreementName) {
		this.partnerAgreementName = partnerAgreementName;
	}
	public String getServiceProviderReferenceNumber() {
		return serviceProviderReferenceNumber;
	}
	public void setServiceProviderReferenceNumber(
			String serviceProviderReferenceNumber) {
		this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
	}
	public String getServiceRequestStatus() {
		return serviceRequestStatus;
	}
	public void setServiceRequestStatus(String serviceRequestStatus) {
		this.serviceRequestStatus = serviceRequestStatus;
	}
	public void setLineItems(ArrayList<PartnerPaymentRequestExpenseDO> lineItems) {
		this.lineItems = lineItems;
	}
	public ArrayList<PartnerPaymentRequestExpenseDO> getLineItems() {
		return lineItems;
	}
	public String getSupplyAgreementFee() {
		return supplyAgreementFee;
	}
	public void setSupplyAgreementFee(String supplyAgreementFee) {
		this.supplyAgreementFee = supplyAgreementFee;
	}

	protected static Date convertStringToDateWithOutMarker (String dateString)  {
		return LangUtil.convertStringToGMTDate(dateString);
	}
	
	
}
