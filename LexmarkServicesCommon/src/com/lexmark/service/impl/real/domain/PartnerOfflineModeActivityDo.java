package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * @author i.prikhodko
 *
 *	do-mapping: "do-partnerofflinemodeactivitydo-mapping.xml"
 */

public class PartnerOfflineModeActivityDo extends PartnerActivityBase {

	private static final long serialVersionUID = 1L;

	private Boolean employeeFlag;
	private String queryType;
	private String mdmLevel;
	private ArrayList<PartnerOfflineModeActivityAccountTypeDo> partnerAccountTypes;
	private ArrayList<PartnerOfflineModeActivityListPartDo> partsList;
	private String overrideOffering;
	private String claimStatusDate;
	private ArrayList<OfflineModeRequestAttachmentDO> offlineModeAttachments;
	private String serviceRequestWebStatus;
	
	public Boolean isEmployeeFlag() {
		if(employeeFlag == null) {
			employeeFlag = false;
		}
		return employeeFlag;
	}

	public void setEmployeeFlag(Boolean employeeFlag) {
		this.employeeFlag = employeeFlag;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setPartneraccountTypes(ArrayList<PartnerOfflineModeActivityAccountTypeDo> partneraccountTypes) {
		this.partnerAccountTypes = partneraccountTypes;
	}

	public ArrayList<PartnerOfflineModeActivityAccountTypeDo> getPartneraccountTypes() {
		return partnerAccountTypes;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}
	public Boolean getEmployeeFlag() {
		return employeeFlag;
	}

    public String getOverrideOffering() {
        return overrideOffering;
    }

    public void setOverrideOffering(String overrideOffering) {
        this.overrideOffering = overrideOffering;
    }

	public ArrayList<PartnerOfflineModeActivityListPartDo> getPartsList() {
		return partsList;
	}

	public void setPartsList(ArrayList<PartnerOfflineModeActivityListPartDo> partsList) {
		this.partsList = partsList;
	}

	public String getClaimStatusDate() {
		return claimStatusDate;
	}

	public void setClaimStatusDate(String claimStatusDate) {
		this.claimStatusDate = claimStatusDate;
	}

	public ArrayList<OfflineModeRequestAttachmentDO> getOfflineModeAttachments() {
		return offlineModeAttachments;
	}

	public void setOfflineModeAttachments(
			ArrayList<OfflineModeRequestAttachmentDO> offlineModeAttachments) {
		this.offlineModeAttachments = offlineModeAttachments;
	}

	public String getServiceRequestWebStatus() {
		return serviceRequestWebStatus;
	}

	public void setServiceRequestWebStatus(String serviceRequestWebStatus) {
		this.serviceRequestWebStatus = serviceRequestWebStatus;
	}

	@Override
	public int hashCode() {
		String serviceRequestNumber = getServiceRequestNumber();
		final int prime = 31;
		int result = 17;
		result = prime * result + ((serviceRequestNumber == null) ? 0 : serviceRequestNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PartnerOfflineModeActivityDo))
			return false;
		String serviceRequestNumber = getServiceRequestNumber();
		PartnerOfflineModeActivityDo other = (PartnerOfflineModeActivityDo) obj;
		if (serviceRequestNumber == null) {
			if (other.getServiceRequestNumber() != null)
				return false;
		} else if (!serviceRequestNumber.equalsIgnoreCase(other.getServiceRequestNumber()))
			return false;
		return true;
	}
	
}
