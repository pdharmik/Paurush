package com.lexmark.service.impl.real.domain;

import java.io.Serializable;

import com.amind.common.domain.BaseEntity;

/**
 * mapping file: "do-entitlementtemplateservicedetailsdo-mapping.xml"
 */
public class EntitlementTemplateServiceDetailsDo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2404379110556034165L;

	private String description;
	private Boolean primaryFlag;
	private String entitlementId;
	private boolean customerShowFlag;
	
	//added by ragesree -2098 start
	private boolean partnerShowFlag;
	
	public boolean isPartnerShowFlag() {
		return partnerShowFlag;
	}
	public void setPartnerShowFlag(boolean partnerShowFlag) {
		this.partnerShowFlag = partnerShowFlag;
	}
	//added by ragesree -2098 end
	public boolean isCustomerShowFlag() {
		return customerShowFlag;
	}
	public void setCustomerShowFlag(boolean customerShowFlag) {
		this.customerShowFlag = customerShowFlag;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setPrimaryFlag(Boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
	}
	public Boolean getPrimaryFlag() {
		return primaryFlag;
	}
	public String getEntitlementId() {
		return entitlementId;
	}
	public void setEntitlementId(String entitlementId) {
		this.entitlementId = entitlementId;
	}

}
