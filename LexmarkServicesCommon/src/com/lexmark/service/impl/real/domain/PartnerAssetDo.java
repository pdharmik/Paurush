package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * mapping file: "do-partnerasset-mapping.xml"
 */
public class PartnerAssetDo extends AssetBase{
	private static final long serialVersionUID = -3895494597906215907L;
	
	private String customerAccountId;
	private String customerAccountName;
	private String partnerAccountId;
	private String partnerAccountName;
	private String shipAddressId;
	private String shipAddressName;
	private String shipAddress1;
	private String shipAddress2;
	private String shipAddress3;
	private String shipCity;
	private String shipProvince;
	private String shipState;
	private String shipCountry;
	private String shipPostalCode;
	private String partnerAccountType;
	private Boolean orderPartsFlag;
	private Boolean createShipToAddressFlag;
	private Boolean createClaimFlag;
	private Boolean allowAdditionalPaymentRequestFlag;
	private String organizationId;
	private String partQuantityOrderLimit;
	private String partnerAccountCurrency;
	private ArrayList<PartnerAssetFlagDo> flags;
	private String customerReportingName;
	private String mki;
	private String serviceProvider;
	
	public String getMki() {
		return mki;
	}

	public void setMki(String mki) {
		this.mki = mki;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	
	public ArrayList<PartnerAssetFlagDo> getFlags() {
		return flags;
	}
	public void setFlags(ArrayList<PartnerAssetFlagDo> flags) {
		this.flags = flags;
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
	public String getShipAddressId() {
		return shipAddressId;
	}
	public void setShipAddressId(String shipAddressId) {
		this.shipAddressId = shipAddressId;
	}
	public String getShipAddressName() {
		return shipAddressName;
	}
	public void setShipAddressName(String shipAddressName) {
		this.shipAddressName = shipAddressName;
	}
	public String getShipAddress1() {
		return shipAddress1;
	}
	public void setShipAddress1(String shipAddress1) {
		this.shipAddress1 = shipAddress1;
	}
	public String getShipAddress2() {
		return shipAddress2;
	}
	public void setShipAddress2(String shipAddress2) {
		this.shipAddress2 = shipAddress2;
	}
	public String getShipAddress3() {
		return shipAddress3;
	}
	public void setShipAddress3(String shipAddress3) {
		this.shipAddress3 = shipAddress3;
	}
	public String getShipCity() {
		return shipCity;
	}
	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}
	public String getShipProvince() {
		return shipProvince;
	}
	public void setShipProvince(String shipProvince) {
		this.shipProvince = shipProvince;
	}
	public String getShipState() {
		return shipState;
	}
	public void setShipState(String shipState) {
		this.shipState = shipState;
	}
	public String getShipCountry() {
		return shipCountry;
	}
	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}
	public String getShipPostalCode() {
		return shipPostalCode;
	}
	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}
	public String getPartnerAccountType() {
		return partnerAccountType;
	}
	public void setPartnerAccountType(String partnerAccountType) {
		this.partnerAccountType = partnerAccountType;
	}
	public boolean isOrderPartsFlag() {
		return orderPartsFlag;
	}
	public void setOrderPartsFlag(Boolean orderPartsFlag) {
		this.orderPartsFlag = orderPartsFlag;
	}
	public Boolean isCreateShipToAddressFlag() {
		return createShipToAddressFlag;
	}
	public void setCreateShipToAddressFlag(Boolean createShipToAddressFlag) {
		this.createShipToAddressFlag = createShipToAddressFlag;
	}
	public boolean isCreateClaimFlag() {
		return createClaimFlag;
	}
	public void setCreateClaimFlag(Boolean createClaimFlag) {
		this.createClaimFlag = createClaimFlag;
	}
	public Boolean isAllowAdditionalPaymentRequestFlag() {
		return allowAdditionalPaymentRequestFlag;
	}
	public void setAllowAdditionalPaymentRequestFlag(
			Boolean allowAdditionalPaymentRequestFlag) {
		this.allowAdditionalPaymentRequestFlag = allowAdditionalPaymentRequestFlag;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getPartQuantityOrderLimit() {
		return partQuantityOrderLimit;
	}
	public void setPartQuantityOrderLimit(String partQuantityOrderLimit) {
		this.partQuantityOrderLimit = partQuantityOrderLimit;
	}
	public void setPartnerAccountCurrency(String partnerAccountCurrency) {
		this.partnerAccountCurrency = partnerAccountCurrency;
	}
	public String getPartnerAccountCurrency() {
		return partnerAccountCurrency;
	}
	public String getCustomerReportingName() {
		return customerReportingName;
	}
	public void setCustomerReportingName(String customerReportingName) {
		this.customerReportingName = customerReportingName;
	}
}
