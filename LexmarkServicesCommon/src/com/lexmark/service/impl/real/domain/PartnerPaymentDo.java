package com.lexmark.service.impl.real.domain;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.lexmark.util.LangUtil;

/**
 * mapping file: "do-partnerpayment-mapping.xml"
 */
public class PartnerPaymentDo extends AccountBasedDo {
	private static final long serialVersionUID = -5502243915803811174L;
	
	private String dateCreated;
	private String paymentNumber;
	private String partnerAgreement;
	private String checkNumber;
	private String paymentTotal;
	private String payableToName;	
	private String partnerAccountId;
	private String partnerAccountName;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String province;
	private String postalCode;
	private String country;
	private String state;
	private String paymentStatus;
	private Boolean allowAdditionalPaymentRequestFlag;
	private String addressId;
	private String addressName;
	private String employeeId;
	private String paymentId;
	private String mdmLevel;
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public Date getDateCreated() {
		return convertStringToDateWithOutMarker(dateCreated);
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getPartnerAgreement() {
		return partnerAgreement;
	}
	public void setPartnerAgreement(String partnerAgreement) {
		this.partnerAgreement = partnerAgreement;
	}
	public String getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	public String getPaymentTotal() {
		if(StringUtils.isEmpty(paymentTotal)) {
			return "0";
		}
		return paymentTotal;
	}
	public void setPaymentTotal(String paymentTotal) {
		this.paymentTotal = paymentTotal;
	}
	public String getPayableToName() {
		return payableToName;
	}
	public void setPayableToName(String payableToName) {
		this.payableToName = payableToName;
	}
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
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Boolean getAllowAdditionalPaymentRequestFlag() {
		return allowAdditionalPaymentRequestFlag;
	}
	public void setAllowAdditionalPaymentRequestFlag(
			Boolean allowAdditionalPaymentRequestFlag) {
		this.allowAdditionalPaymentRequestFlag = allowAdditionalPaymentRequestFlag;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	protected static Date convertStringToDateWithOutMarker (String dateString)  {
		return LangUtil.convertStringToGMTDate(dateString);
	}
}
